# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/42747

# 문제 접근법

논문의 인용 횟수를 내림차순으로 정렬하였다.

앞에서부터 순회하면서 현재까지 확인한 논문 수(`i + 1`)와 현재 논문의 인용 횟수를 비교하였다.

현재 논문의 인용 횟수가 `i + 1` 이상이라면 H-Index의 조건을 만족하므로 `answer`를 갱신하였다. 조건을 만족하는 가장 마지막 값이 최대 H-Index가 된다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> citations) {
    int answer = 0;
    sort(citations.begin(), citations.end(),greater<>());
    
    for(int i = 0; i < citations.size(); i++){
        if(citations[i] >= i+1)
            answer = i+1;
    }
    return answer;
}
```

# 새로 알게 된 점

* H-Index는 **H번 이상 인용된 논문이 H편 이상인지**를 확인하는 문제라는 것을 이해하였다.
* `sort(v.begin(), v.end(), greater<int>())`를 이용하면 인용 횟수를 내림차순으로 정렬할 수 있다.
* 내림차순으로 정렬하면 `citations[i] >= i + 1` 조건만으로 H-Index를 판별할 수 있다.
* `if`문 뒤에 실수로 세미콜론(`;`)을 붙이면 조건문이 무시되고 다음 코드가 항상 실행된다는 점을 다시 확인하였다.

# 느낀점

처음에는 H-Index의 정의가 어려웠지만, 예시를 통해 조건을 하나씩 확인해 보니 규칙을 이해할 수 있었다. 또한 정렬을 이용하면 복잡한 계산 없이 간단한 조건 비교만으로 해결할 수 있다는 점을 배울 수 있었고, 사소한 문법 실수인 `if` 뒤 세미콜론도 다시 한번 주의해야겠다고 느꼈다.
