## 👀 제한 시간 안에 어디까지 해냈는가?

- Java로 처음 도전하여 `13분 01초`만에 문제를 풀었다.
    - 다만 Java 코드 작성 과정에서 문자열 비교 로직을 `==` 로 작성한 실수를 하여 시간을 좀 허비했다.
    - 문자열 비교는 `s1.equals(s2);`로 작성해야한다.
- Python으로 2차 도전을 진행하여 `2분 55초`만에 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

여러 종류의 괄호로 구성된 문자열이 주어질 때 해당 문자열의 모든 괄호가 쌍에 맞춰 닫히는지 여부를 반환하는 문제이다.

문제에 주어지는 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= s.length <= 104`
- `s` consists of parentheses only `'()[]{}'`.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

전형적인 Stack 연습 문제이다. Stack을 선언하여 여는 괄호는 닫는 괄호로 변경해 stack에 쌓고, 닫힌 괄호는 stack이 비었는지, 가장 위에 있는게 자신의 쌍인지 검사하여 stack 위 요소를 pop하거나 false를 반환하면서 순회를 이어나간다.

이후 순회가 끝나면 stack이 비었는지 여부를 통해 정답을 반환하도록 코드를 작성하면 된다.

### 결론

- 쉬운 Stack 연습 문제이다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

혼자서 무리없이 코드를 작성했다. 다만 Java 코드 작성 과정에서 문자열 비교 로직 작성에 실수를 하나 했다.

- 처음 문자열 비교 로직을 `s1 == s2` 로 작성했는데, 이렇게 하면 제대로 된 문자열 값 비교가 이루어지지 않는다.
- 문자열 값 비교는 `s1.equals(s2)`로 로직을 작성해야한다.

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    class Solution:
        def isValid(self, s: str) -> bool:
            stack = []
            item = {"(": ")", "{": "}", "[": "]"}
            
            for c in s:
                if c in item:
                    stack.append(item[c])
                else:
                    if not stack or stack[-1] != c:
                        return False
                    stack.pop()
            
            return not stack
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        public boolean isValid(String s) {
            Deque<String> dq = new ArrayDeque<>();
            Map<String, String> item = Map.of(
                "(", ")",
                "{", "}",
                "[", "]"
            );
    
            for (int i = 0; i < s.length(); i++) {
                String cur = String.valueOf(s.charAt(i));
    
                if (item.containsKey(cur)) {
                    dq.push(item.get(cur));
                } else {
                    if (dq.isEmpty() || !dq.peek().equals(cur)) {
                        return false;
                    }
    
                    dq.pop();
                }
            }
    
            return dq.isEmpty();
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- Java의 문자열 값 비교 로직을 복습할 수 있었다.