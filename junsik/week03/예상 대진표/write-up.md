# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/12985

# 문제 접근법

1. 토너먼트에서 다음 라운드 번호는 `(현재 번호 + 1) / 2` 로 계산할 수 있다.
2. A와 B의 번호를 매 라운드마다 갱신한다.
3. A와 B의 번호가 같아지는 순간 두 사람이 만난 것이므로 반복을 종료한다.
4. 번호를 갱신한 횟수(라운드 수)를 정답으로 반환한다.

# 소스코드

```cpp
#include <string>
#include <vector>

using namespace std;

int solution(int n, int a, int b)
{
    int answer = 0;

    while(a != b){
        a = (a + 1) / 2;
        b = (b + 1) / 2;
        answer++;
    }

    return answer;
}
```

# 새로 알게 된 점

* 토너먼트 대진표에서는 다음 라운드 번호를 `(번호 + 1) / 2`로 구할 수 있다.
* 실제 대진표를 만들 필요 없이 참가자 번호만 갱신하여 문제를 해결할 수 있다.
* 문제를 단순히 시뮬레이션하는 방식으로도 효율적으로 해결할 수 있다.

# 느낀점
처음에는 대진표를 직접 구현해야 하는 문제라고 생각했지만, 참가자 번호의 규칙만 찾으면 매우 간단하게 해결할 수 있는 문제였다.