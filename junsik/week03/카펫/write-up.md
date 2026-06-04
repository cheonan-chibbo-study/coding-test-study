# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/42842

# 문제 접근법

1. 카펫 전체 칸 수를 구한다.
   * `total = brown + yellow`
2. 전체 칸 수의 약수쌍을 찾는다.
3. 각 약수쌍을 `(가로, 세로)`로 가정한다.
4. 내부 노란색 칸의 개수인 `(가로 - 2) * (세로 - 2)`를 계산한다.
5. 계산 결과가 `yellow`와 같으면 정답이다.

# 소스코드

```cpp
#include <string>
#include <vector>

using namespace std;

vector<int> solution(int brown, int yellow) {
    int total = brown + yellow;

    for(int i = 1; i * i <= total; i++) {

        if(total % i == 0) {

            int w = total / i;
            int h = i;

            if((w - 2) * (h - 2) == yellow) {
                return {w, h};
            }
        }
    }

    return {};
}
```

# 새로 알게 된 점

* 직사각형의 가로, 세로는 전체 칸 수의 약수쌍으로 구할 수 있다.
* 약수 탐색은 `i * i <= total`까지만 확인해도 모든 경우를 찾을 수 있다.

# 느낀점

문제를 그래프 탐색 관점으로만 보지 말고, 약수나 수식을 활용할 수 있는지도 함께 생각해야 한다는 것을 배울 수 있었다.