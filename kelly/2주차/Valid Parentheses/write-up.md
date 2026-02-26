## 👀 제한 시간 안에 어디까지 해냈는가?

`5분 56초`만에 풀었다. 제출한 코드는 다음과 같다.

```python
class Solution:
    def isValid(self, s: str) -> bool:
        open_v = ["(", "{", "["]

        queue = []
        for v in s:
            if v in open_v:
                if v == "(":
                    queue.append(")")
                elif v == "{":
                    queue.append("}")
                else:
                    queue.append("]")
            else:
                if len(queue) == 0 or queue[-1] != v:
                    return False
                else:
                    queue.pop()
        
        return len(queue) == 0
        
```

---

## 🧑‍🔬 문제 분석

괄호 (`()` , `{}` , `[]`)로 이루어진 문자열이 주어졌을 때 모든 괄호의 짝을 맞출 수 있는지 여부를 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= s.length <= 10^4`
- `s` consists of parentheses only `'()[]{}'`.

---

## 🤔 풀이 고민

### Stack 활용

괄호 맞추기 문제는 전형적인 Stack 문제이다. 별 고민 없이 Stack을 사용해 문제를 해결했다. 문제 주어지는 최대 문자열 길이 역시 10^4 이기 때문에 stack의 append/pop 연산으로 충분히 해결할 수 있다.

### 결론

- 전형적인 Stack 활용 문제이다.

---

## 🏃 코드 작성 과정

### 최종 정답 코드 개선

코드 작성이 크게 어렵지는 않았지만 각 여는 괄호 케이스를 비교하는 로직을 작성할 때 if-else문이 제법 길어져 고민이 많았다. 우선 정답 처리를 받는게 우선이므로 당장 작성한 코드는 아래와 같다.

```python
class Solution:
    def isValid(self, s: str) -> bool:
        open_v = ["(", "{", "["]

        queue = []
        for v in s:
            if v in open_v:
                if v == "(":
                    queue.append(")")
                elif v == "{":
                    queue.append("}")
                else:
                    queue.append("]")
            else:
                if len(queue) == 0 or queue[-1] != v:
                    return False
                else:
                    queue.pop()
        
        return len(queue) == 0
        
```

이 코드는 딕셔너리를 활용하면 if-else문을 줄이고 코드를 더 깔끔하게 개선할 수 있다. 그리고 빈 stack 검사도 `not stack` 으로 표현할 수 있다.

```python
class Solution:
    def isValid(self, s: str) -> bool:
        pair = {
            "(": ")",
            "{": "}",
            "[": "]"
        }

        stack = []
        for v in s:
            if v in pair:
                stack.append(pair[v])
            else:
                if not stack or stack[-1] != v:
                    return False
                else:
                    stack.pop()
        
        return not stack
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

---

## 🥰 배운점 & 느낀점

- 문제를 보자마자 Stack을 떠올릴 수 있었다.
- `Easy` 문제이기 때문에 딱히 크게 배울만한건 없었다.
- 딕셔너리를 활용하면 if-else 분기 로직을 줄일 수 있다.
- 빈 stack인지 검사하는 로직은 `not stack` 을 사용할 수도 있다.