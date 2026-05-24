# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/42583

## 문제 접근법

1. 트럭은 주어진 순서대로 처리해야 하며, 현재 다리 위에 있는 트럭들의 총 무게를 관리해야 한다고 판단했다. 그렇기에 `current_weight` 변수를 사용한다.

2. 다리 위에는 여러 트럭이 동시에 있을 수 있기 때문에, 현재 다리 위 모든 트럭의 무게 합을 기준으로 다음 트럭을 올릴 수 있는지 판단한다.

3. 절대적인 시간이 흐르기 때문에 미리 선언되어 있는 `answer`를 전체 소요 시간으로 사용한다.

4. 다리 위 트럭의 이동 상태를 관리하기 위해 `queue`를 사용하고, 매초마다 앞의 값을 제거한 뒤 새 트럭을 올릴 수 있으면 추가하고, 불가능하면 `0`을 추가하는 방식으로 다리 상태를 갱신한다.

### 소스코드
```cpp
#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int answer = 0;
    int current_weight = 0; //다리위 무게
    int index = 0; //순번   
    queue<int> bridge_truck; //다리위 트럭
    
    for(int i = 0; i < bridge_length; i++){
        bridge_truck.push(0);
    }
    
    while(index<truck_weights.size()){
        answer++; //시간 흐르게 하기 (절대값)
        
        current_weight -= bridge_truck.front();
        bridge_truck.pop();
        
        if(current_weight + truck_weights[index] <= weight){
            bridge_truck.push(truck_weights[index]);
            
            current_weight += truck_weights[index];

            index++;
        }
        else{
            bridge_truck.push(0);
        }
    }
    
    answer += bridge_length;
    
    return answer;
}
```

## 느낀점
다리가 버틸 수 있는 무게와 트럭의 무게의 관계성이 이해된 순간 생각보다 쉽다고 생각하였으나, 절대적인 시간 개념도 필요하고 다리위에 하나의 트럭뿐만 아니라 2대 혹은 3대의 트럭이 지나갈수있는 경우의 수가 존재해 생각보다 문제를 푸는데 오래걸렸다.