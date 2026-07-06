# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/42883

# 문제 접근법

숫자를 왼쪽부터 하나씩 확인하면서 스택을 이용해 가장 큰 수가 되도록 구성하였다.

현재 숫자가 스택의 맨 위 숫자보다 크고 아직 제거 횟수가 남아 있다면, 스택의 맨 위 숫자를 제거하였다. 이 과정을 더 이상 제거할 수 없을 때까지 반복한 뒤 현재 숫자를 스택에 저장하였다.

모든 숫자를 확인한 후에도 제거 횟수가 남아 있다면 스택의 뒤쪽 숫자부터 제거하였다.

마지막으로 스택에 저장된 숫자를 문자열로 변환하고, 스택의 특성상 순서가 반대로 저장되므로 `reverse()`를 이용하여 원래 순서로 뒤집어 반환하였다.

# 소스코드

```cpp
#include <string>
#include <stack>
#include <algorithm>

using namespace std;

string solution(string number, int k) {
    string answer = "";
    stack<char> st;

    for(char c : number)
    {
        while(!st.empty() && k > 0 && st.top() < c)
        {
            st.pop();
            k--;
        }

        st.push(c);
    }
    while(k > 0)
    {
        st.pop();
        k--;
    }
    while(!st.empty())
    {
        answer += st.top();
        st.pop();
    }

    reverse(answer.begin(), answer.end());
    return answer;
}
```

# 새로 알게 된 점

* `stack<char>`를 이용하면 문자열을 한 글자씩 처리할 수 있다.
* 스택의 맨 위 숫자보다 현재 숫자가 크면 `while`문을 이용해 작은 숫자를 연속해서 제거할 수 있다.
* 모든 숫자를 확인한 뒤에도 `k`가 남아 있다면 스택의 뒤쪽 원소부터 제거해야 한다.
* 스택은 LIFO(Last In, First Out) 구조이므로 `pop()`하여 문자열을 만들면 순서가 반대로 된다. 이때 `reverse()`를 사용하여 원래 순서로 복원할 수 있다.

# 느낀점

처음에는 어떤 자료구조를 사용해야 할지 고민했지만, 스택을 이용하면 이전 숫자와 현재 숫자를 쉽게 비교하며 불필요한 숫자를 제거할 수 있다는 것을 알게 되었다. 또한 `while`문을 이용한 연속 제거와 스택의 특성을 이해하는 데 도움이 되었고, 스택을 활용하는 대표적인 문제 유형을 익힐 수 있었다.
