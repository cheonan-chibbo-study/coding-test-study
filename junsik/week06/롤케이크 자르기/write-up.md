# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/132265

# 문제 접근법

처음에는 모든 토핑을 오른쪽 조각에 있다고 가정하고 `map`을 이용하여 토핑 종류별 개수를 저장하였다.

왼쪽 조각은 `set`을 이용하여 토핑의 종류만 관리하였다.

토핑을 하나씩 왼쪽으로 이동시키면서 왼쪽은 `set`에 추가하고, 오른쪽은 `map`의 개수를 감소시켰다. 오른쪽 토핑의 개수가 0이 되면 `erase()`를 이용해 해당 종류를 제거하였다.

매 이동마다 `left.size()`와 `right.size()`를 비교하여 두 조각의 토핑 종류 수가 같으면 정답을 증가시켰다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <set>
#include <map>

using namespace std;

int solution(vector<int> topping) {
    int answer = 0;
    
    map<int, int> right;

    for(int x : topping)
    {
        right[x]++;
    }

    set<int> left;

    for(int i = 0; i<topping.size(); i++){
        left.insert(topping[i]);

        right[topping[i]]--;

        if(right[topping[i]] == 0)
            right.erase(topping[i]);

        if(left.size() == right.size())
            answer++;
    }
        
    return answer;
}
```

# 새로 알게 된 점

* `set`은 중복을 자동으로 제거하므로 토핑의 **종류 수**를 관리하기에 적합하다.
* `map`은 토핑의 **개수**를 관리할 수 있어, 개수가 0이 되면 `erase()`를 통해 종류를 제거할 수 있다.
* `map.size()`는 현재 저장되어 있는 **서로 다른 key의 개수**를 반환한다.
* 범위 기반 `for`문에서는 인덱스가 아니라 **원소의 값**을 순회한다는 점을 다시 확인할 수 있었다.

# 느낀점

처음에는 자를 때마다 양쪽의 토핑 종류를 다시 계산하려고 했지만, 토핑을 하나씩 왼쪽으로 이동시키며 상태를 갱신하는 방식으로 훨씬 효율적으로 해결할 수 있었다. `set`과 `map`을 함께 사용하는 방법과 두 자료구조의 역할 차이를 이해할 수 있었던 문제였다.
