# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/389478

# 문제 접근법

상자를 실제로 이차원 배열에 저장하지 않고, 상자 번호를 이용하여 `(row, col)` 좌표를 계산하였다.
행은 `(num - 1) / w`를 이용해 구하고, 열은 행의 짝수/홀수 여부에 따라 다르게 계산하였다.
이후 현재 상자와 같은 열에 있는 위쪽 행들을 탐색하면서 실제 존재하는 상자인지 확인하고 개수를 세어 정답을 구하였다.

# 소스코드

```cpp
#include <string>
#include <vector>

using namespace std;

int solution(int n, int w, int num) {
    int answer = 1;
    int totalRows = (n + w - 1) / w;
    int row = (num - 1) / w;
    int col;

    if(row % 2 == 0)
        col = (num - 1) % w;
    else
        col = w - 1 - ((num - 1) % w);

    for(int r = row + 1; r < totalRows; r++)
    {
        int boxNum;
        
        if(r % 2 == 0)
            boxNum = r * w + col + 1;

        else
            boxNum = r * w + (w - col);

        if(boxNum <= n)
            answer++;
    }

    return answer;
}
```

# 새로 알게 된 점

* 실제로 배열을 만들지 않고 규칙을 이용해 좌표를 계산할 수 있다.
* 지그재그 형태는 행의 짝수/홀수 여부에 따라 처리 방식을 나눌 수 있다.
* 구현 문제는 불필요한 자료구조를 사용하지 않는 것도 중요하다.

# 느낀점

처음에는 이차원 배열을 만들어 해결하려고 했지만, 상자를 저장하지 않고 좌표를 직접 계산하는 방식이 더 효율적이라는 것을 배울 수 있었다. 규칙을 수식으로 바꾸는 연습이 된 문제였다.
