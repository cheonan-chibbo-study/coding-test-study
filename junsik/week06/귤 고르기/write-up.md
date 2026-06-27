# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/138476

# 문제 접근법

`map`을 이용하여 각 귤 크기별 개수를 저장하였다.

`map`의 value(개수)만 `vector`에 저장한 뒤, 개수가 많은 순서대로 선택하기 위해 내림차순 정렬을 수행하였다.

이후 앞에서부터 개수를 누적하며 `k` 이상이 되는 순간 사용한 귤의 종류 수를 반환하였다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

int solution(int k, vector<int> tangerine) {
    int answer = 0;
    int sum = 0;
    map<int, int> cnt;

    for (int x : tangerine)
    {
        cnt[x]++;
    }
    
    vector<int> box;

    for(auto item : cnt){
        box.push_back(item.second);
    }

    sort(box.begin(), box.end(), greater<int>());

    for(int x : box){
        sum += x;
        answer++;
        if(sum >= k)
            return answer;
    }
}
```

# 새로 알게 된 점

* `map`을 순회할 때 `first`는 key, `second`는 value를 의미한다.
* `map`의 value만 `vector`에 저장하여 원하는 기준으로 정렬할 수 있다.
* `sort(v.begin(), v.end())`는 오름차순 정렬이며, `sort(v.begin(), v.end(), greater<int>())`를 사용하면 내림차순으로 정렬할 수 있다.
* 범위 기반 `for`문(`for(int x : v)`)에서는 `x`가 인덱스가 아니라 원소의 값이라는 점을 알게 되었다.

# 느낀점

처음에는 DFS와 같은 탐색 알고리즘을 생각했지만, 실제로는 빈도수를 계산하고 정렬하는 그리디 방식으로 해결하는 문제였다. `map`과 `vector`를 함께 활용하는 방법과 내림차순 정렬, 범위 기반 `for`문의 동작을 익힐 수 있는 좋은 문제였다.
