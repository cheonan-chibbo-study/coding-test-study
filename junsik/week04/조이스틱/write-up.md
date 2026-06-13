# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/42860

# 문제 접근법

조작을 두 가지로 나누어 생각하였다.

* 위/아래 이동 : 각 문자를 변경하는 최소 횟수 계산
* 좌/우 이동 : 커서를 이동하는 최소 횟수 계산

위/아래 이동은 각 문자마다 위로 이동하는 경우와 아래로 이동하는 경우 중 작은 값을 선택하였다.

좌/우 이동은 기본적으로 오른쪽으로 끝까지 이동한다고 가정한 후, 연속된 A 구간을 발견하면 되돌아가는 경우도 고려하여 최소 이동 횟수를 구하였다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(string name) {
    int answer = 0;
    int move = name.size() - 1;

    for (int i = 0; i < name.size(); i++) {

        // 위/아래 조작 횟수
        answer += min(name[i] - 'A', 'Z' - name[i] + 1);

        // 연속된 A 찾기
        int next = i + 1;
        while (next < name.size() && name[next] == 'A') {
            next++;
        }

        // 커서 이동 최소값 갱신
        move = min(move,
                   i * 2 + (int)name.size() - next);

        move = min(move,
                   ((int)name.size() - next) * 2 + i);
    }

    answer += move;

    return answer;
}
```

# 새로 알게 된 점

* 그리디는 문제를 여러 부분으로 나누어 최적의 선택을 하는 방식으로 접근할 수 있다.
* 연속된 A 구간을 활용하면 커서 이동 횟수를 줄일 수 있다.

# 느낀점

처음에는 단순 구현 문제처럼 보였지만, 실제로는 좌우 이동을 최소화하는 그리디 아이디어가 핵심인 문제였다.