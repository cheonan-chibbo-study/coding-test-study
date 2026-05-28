# 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/132267

## 문제 접근법
1. 현재 빈병으로 받을 수 있는 콜라 수를 계산
2. 받은 콜라 개수를 answer에 합치기
3. 남은 빈 병 + 새로 받은 콜라의 병 수를 n에 갱신

## 소스코드
```cpp
#include <string>
#include <vector>

using namespace std;

int solution(int a, int b, int n) {
    int answer = 0;
    
    while (n >= a) {
        int cola = n / a * b;
        answer += cola;
        n = (n%a) + cola;
    }

    return answer;
}
```

## 느낀 점
문제를 처음봤을땐 이거를 어떻게 코드를 적용시키지 생각했으나, 하나하나 천천히 계산과정을 뜯으면서 코드에 적용했더니 쉽게 풀렸다.