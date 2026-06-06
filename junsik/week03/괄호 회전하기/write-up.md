# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/76502

# 문제 접근법

1. 문자열을 왼쪽으로 한 칸씩 회전한다.
2. 회전된 문자열이 올바른 괄호 문자열인지 스택을 이용해 검사한다.
3. 올바른 괄호 문자열이면 answer를 증가시킨다.
4. 문자열 길이만큼 반복하여 모든 회전 경우를 확인한다.

올바른 괄호 문자열 판별 방법

* 여는 괄호 `(`, `[`, `{` 는 스택에 저장한다.
* 닫는 괄호를 만나면 스택이 비어있는지 확인한다.
* 스택의 top과 현재 괄호가 짝이 맞는지 검사한다.
* 짝이 맞으면 pop하고, 맞지 않으면 실패한다.
* 모든 문자를 검사한 후 스택이 비어있으면 올바른 괄호 문자열이다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <stack>

using namespace std;

bool check(string s) {
    stack<char> test;

    for(char c : s) {

        if(c == '(' || c == '[' || c == '{') {
            test.push(c);
        }
        else {
            if(st.empty())
                return false;

            if(c == ')' && test.top() != '(')
                return false;

            if(c == ']' && test.top() != '[')
                return false;

            if(c == '}' && test.top() != '{')
                return false;

            test.pop();
        }
    }

    return test.empty();
}

int solution(string s) {
    int answer = 0;

    for(int i = 0; i < s.size(); i++) {

        if(check(s))
            answer++;

        char first = s[0];
        s.erase(s.begin());
        s.push_back(first);
    }

    return answer;
}
```

# 새로 알게 된 점

* 스택은 괄호 짝을 검사할 때 매우 유용한 자료구조이다.
* 올바른 괄호 문자열은 여는 괄호를 스택에 저장하고 닫는 괄호가 나올 때 짝을 비교하여 확인할 수 있다.
* 문자열 회전은 `erase()`와 `push_back()`을 이용하여 구현할 수 있다.
* 기능을 함수로 분리하면 코드가 훨씬 읽기 쉬워진다.

# 느낀점

처음에는 괄호를 어떻게 비교해야 할지 헷갈렸지만, 스택에는 여는 괄호를 저장하고 닫는 괄호가 나올 때 top과 비교한다는 원리를 이해하고 나니 해결할 수 있었다.

또한 회전 기능과 괄호 검사 기능을 별도의 함수로 분리하니 문제를 단계적으로 해결할 수 있었고, 스택의 활용 방법을 익힐 수 있는 좋은 문제였다.