# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/87390

# 문제 접근법

처음에는 실제로 `n × n` 배열을 생성한 뒤 1차원 배열로 변환하여 구간을 자르려고 생각했지만, `n`의 범위가 매우 커 메모리 초과가 발생할 수 있다는 점을 확인하였다.

배열을 직접 만들지 않고, 1차원 배열의 인덱스를 이용하여 해당 위치의 행(`row`)과 열(`col`)을 계산하였다.

각 위치의 값은 `max(row, col) + 1`이라는 규칙을 이용하여 바로 구할 수 있으므로, `left`부터 `right`까지의 값만 계산하여 정답 배열에 저장하였다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(int n, long long left, long long right) {
    vector<int> answer;
    
    for(long long idx = left; idx <= right; idx++){
        long long row = idx / n;
        long long col = idx % n;
        int value = max(row, col) + 1;
        answer.push_back(value);
    }
    return answer;
}
```

# 새로 알게 된 점

* 1차원 배열의 인덱스를 이용해 `row = idx / n`, `col = idx % n`으로 2차원 배열의 좌표를 구할 수 있다.
* 배열 전체를 생성하지 않고 규칙을 이용해 필요한 값만 계산하는 방식이 메모리와 시간을 크게 절약할 수 있다.
* `max(row, col) + 1`만으로 해당 위치의 값을 바로 구할 수 있다는 규칙을 발견하였다.
* `left`, `right`가 `long long`이므로 반복문 변수도 `long long`으로 선언해야 오버플로우를 방지할 수 있다.

# 느낀점

처음에는 배열을 직접 생성해야 한다고 생각했지만, 규칙을 찾아 필요한 값만 계산하는 방식으로 해결할 수 있었다. 구현보다 규칙을 찾아 수식으로 표현하는 사고가 더 중요하다는 것을 배울 수 있었던 문제였다.
