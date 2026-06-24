# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/12911

# 문제 접근법

먼저 `__builtin_popcount()`를 이용하여 현재 숫자의 1의 개수를 저장하였다.

이후 `n+1`부터 하나씩 증가시키면서 1의 개수를 비교하였다.

원래 숫자의 1의 개수와 동일한 숫자를 찾으면 바로 반환하도록 구현하였다.

# 소스코드

```cpp
#include <string>

using namespace std;

int solution(int n) {
    int answer = 0;
    int count = __builtin_popcount(n);
    n++;
    
    while(true){
        int current = __builtin_popcount(n);
        
        if(count == current){
            answer = n;
            return answer;
        }
        n++;
    }
    return answer;
}
```

# 새로 알게 된 점

* `__builtin_popcount()` 함수를 사용하면 정수를 2진수로 직접 변환하지 않아도 1의 개수를 빠르게 구할 수 있다.
* `__builtin_popcount(n)`은 정수 `n`의 이진수 표현에서 1의 개수를 반환한다.
* 모든 경우를 복잡하게 계산하기보다, 조건을 만족할 때까지 하나씩 증가시키며 탐색하는 브루트포스 방식도 효율적으로 사용할 수 있다.

# 느낀점

처음에는 숫자를 직접 2진수 문자열로 변환해서 해결하려고 했지만, 내장 함수를 활용하면 훨씬 간단하게 해결할 수 있다는 것을 배울 수 있었다. 문제의 규칙을 복잡하게 만들기보다 제공되는 함수를 적절히 활용하는 것이 중요하다는 것을 느꼈다.
