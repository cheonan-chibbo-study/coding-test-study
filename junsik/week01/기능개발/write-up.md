# 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/42586

# 문제 접근 방법
1. 첫번째 벡터에 들어가는 요소들과 두 번째 벡터에 들어가는 요소들을 비교한다.
2. 입출력의 예를 보면 93과 1를 비교했을 때, 100에 도달할때까지의 횟수를 구한다.

```cpp
#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    
    for(int i = 0; i < progresses.size(); i++){
        int start = progresses[i];
        int end = speeds[i];
        int count = 0;
        while(start < 100){
            start += end;
            count++;
        }
        answer.push_back(count);
    }
    
    return answer;
}
```

3. 허나 이렇게만 했을때는 결과값 벡터가 [7,3,9]만 나오게 되며, 이는 예상 결과값인 [2,1]과는 다르다.

## 오답노트
1. 단순히 문제 접근 방법의 2번으로만 하면 되는줄 알았으나, 문제의 해석을 잘못했다.
2. [7,3,9]는 단순히 프로그레스들이 100에 도달할때까지의 날짜일 뿐, 완료된 날짜들을 기준으로 배포 묶음 갯수를 구하는것이 답이다. 

``` cpp
#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    queue<int> days;
    
    for(int i = 0; i < progresses.size(); i++){
        int start = progresses[i];
        int end = speeds[i];
        int count = 0;
        while(start < 100){
            start += end;
            count++;
        }
        
        days.push(count);
    }
    
    while(!days.empty()){
        int standard = days.front();
        days.pop();
        
        int deployCount = 1;
        
        while(!days.empty() && days.front() <= standard){
            days.pop();
            deployCount++;
        }
        
        answer.push_back(deployCount);
    }
    
    return answer;
}
```

3. 따라서 문제 유형이 스택/큐이기에 계산된 날짜들을 days라는 큐에 저장하고 배포 묶음 갯수를 다시 구하여 answer에 저장한다.

## 느낀점
문제를 끝까지 읽자.