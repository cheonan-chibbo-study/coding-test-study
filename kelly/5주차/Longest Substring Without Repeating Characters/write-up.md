## 👀 제한 시간 안에 어디까지 해냈는가?

`15분 27초`만에 혼자 문제를 해결하는데 성공했다. 다만 작성한 코드의 시간 복잡도가 살짝 비효율적인거 같아서 다른 풀이도 찾아보려고 한다.

아래는 내가 직접 작성해서 최종 정답 처리를 받은 코드이다.

```python
from collections import deque

class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        answer = float('-inf')
        c_set = set()
        dq = deque()

        if not s:
            return 0

        for c in s:
            if c not in c_set:
                c_set.add(c)
                dq.append(c)
                answer = max(answer, len(c_set))
            else:
                while True:
                    poped = dq.popleft()
                    if poped == c:
                        break
                dq.append(c)
                c_set = set(dq)

        return answer
```

---

## 🧑‍🔬 문제 분석

문자열 s가 주어졌을 때 문자열 s의 문자 중복 없는 부분 문자열 중 가장 긴 문자열의 길이를 반환하는 문제이다.

주어진 제약 조건은 다음과 같다.

**Constraints:**

- `0 <= s.length <= 5 * 104`
- `s` consists of English letters, digits, symbols and spaces.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

이번 문제는 뭔가 특별한 알고리즘을 사용하기 보다는 적절한 자료 구조 + 구현이 필요한 문제였다. 문제에 주어지는 문자열의 최대 길이가 5 * 10^4로 꽤나 긴 문자열이였기 때문에 `O(N^2)`이 걸리는 풀이는 사용할 수 없어 적어도 `O(N)`이 걸리는 풀이를 떠올려야 했다.

그렇게 고민하다 내가 생각해낸 풀이는 다음과 같다.

1. set과 queue를 초기화 한 후, 문자열 전체를 순회한다.
2. 특정 문자열 순회에서 미리 정의한 set에 문자열이 존재하는지 확인 후 존재하지 않으면 중복되지 않는 부분 문자열 요소라 판단하고 set과 queue에 해당 문자를 넣고 현재 set 길이와 이전에 기록된 최대 문자열 길이와 비교해 더 큰쪽으로 갱신한다.
3. 만약 set에 포함되어 있다면 중복된 요소로 판단하고 다음 작업을 처리한다.
    1. 큐의 앞 요소부터 현재 문자까지 요소를 전부 제거한다.
    2. 위에서 갱신한 큐를 기반으로 새로운 set을 만든다.

위 풀이를 코드로 구현해서 제출하니 문제를 해결할 수 있었다.

### 찾아본 풀이

부분 문자열은 주어진 문자열의 연속된 일부분에 해당하는 문자열이다. 부분 문자열 길이의 범위는 0 ~ “주어진 문자열 길이" 이다. 이것을 모두 구해서 문제에서 요구하는 부분 문자열을 찾는 것은 매우 비효율적이기 때문에 주어진 문자열을 처음부터 끝까지 순차적으로 각 문자를 순회하며 정답을 찾아가는 것으로 문제를 해결할 수 있다.

문제에서 요구하는 부분 문자열은 중복 문자가 없어야하며, 길이가 가장 길어야한다. 이를 구하기 위한 방법은 다음과 같다. 주어진 문자열의 앞에서 부터 문자를 한 개 씩 추가하며 부분 문자열을 생성한다. 만약 중복 문자가 발생하지 않으면 계속 문자를 추가하며 부분 문자열의 길이를 늘리고, 중복 문자가 발생한다면, 중복이 사라질 때까지 앞에서부터 문자를 부분 문자열에서 제거한다. 그리고 중복 문자가 발생할 때마다 최대 부분 문자열 길이를 갱신한다.

이 방식을 구현하기 위해서는 2가지 방식을 사용할 수 있다.

1. 해시셋
2. 해시테이블

각 방식 모두 결론적으로 부분 문자열을 구성하는 문자를 확인할 수 있지만, 해시셋은 중복 문자 발생 시 해당 문자를 제거하기 위해 앞에서부터 차례대로 문자를 제거해야 하는 반면, 해시테이블은 문자들의 인덱스를 value에 저장해 즉시 중복 문자를 제거할 수 있다.

### 결론

- 처음 내가 생각한 풀이로 코드를 작성해 제출하면 최종 정답 처리를 받을 수 있다.
- 투 포인터 & 슬라이딩 윈도우를 사용하면 더 효율적인 코드를 작성할 수 있다.

---

## 🏃 코드 작성 과정

### **내가 처음 작성한 코드**

내가 처음 생각해낸 풀이로 작성한 코드는 다음과 같다.

```python
from collections import deque

class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        answer = float('-inf')
        c_set = set()
        dq = deque()

        if not s:
            return 0

        for c in s:
            if c not in c_set:
                c_set.add(c)
                dq.append(c)
                answer = max(answer, len(c_set))
            else:
                while True:
                    poped = dq.popleft()
                    if poped == c:
                        break
                dq.append(c)
                c_set = set(dq)

        return answer
```

### 찾아본 풀이로 작성한 코드

**[ 해시셋 활용 ]**

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if not s:
            return 0
        
        answer = float('-inf')
        n = len(s)
        start = 0
        counter = set()

        for i, c in enumerate(s):
            if c in counter:
                answer = max(answer, i - start)
                while c in counter:
                    counter.remove(s[start])
                    start += 1
            counter.add(c)
        
        answer = max(answer, n - start)
        return answer
```

**[ 해시테이블 활용 ]**

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if not s:
            return 0
        
        answer = float('-inf')
        start = 0
        substr = {}

        for end in range(len(s)):
            if s[end] in substr:
                start = max(start, substr[s[end]] + 1)
            substr[s[end]] = end
            answer = max(end - start + 1, answer)    
        
        return answer
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

---

## 🥰 배운점 & 느낀점

- 내 풀이랑 찾아본 풀이가 크게 다른게 없는거 같은데 찾아본 풀이 코드가 더 빠른 이유가 궁금해서 GPT한테 물어봤다.
    - GPT 답변

      좋은 질문 👍

      겉으로 보면 둘 다 슬라이딩 윈도우 + set이라 원리는 같아 보이지만

      시간복잡도와 상수 비용에서 큰 차이가 난다.

      ──────────────────────────────
      1️⃣ 가장 큰 차이: set 재생성
      ──────────────────────────────

      네 코드에서 가장 느린 부분은:

        ```
        c_set = set(dq)
        ```

      이 코드는 현재 윈도우에 있는 모든 문자를
      다시 복사해서 새로운 set을 만드는 작업이다.

      시간복잡도:
      O(k)   (k = 현재 윈도우 길이)

      이 작업이 반복문 안에서 계속 발생한다.

      최악의 경우:
      "abcdefghijklmnopqrstuvwxyz..."

      처럼 거의 중복 없이 길게 가다가
      중복이 나오는 순간마다

        ```
        set(dq)  → O(n)
        ```

      이 작업이 반복되면서
      전체 시간복잡도가 O(n²)까지 증가할 수 있다.

      ──────────────────────────────
      2️⃣ 남 코드의 핵심 차이
      ──────────────────────────────

      남 코드에서는 set을 절대 재생성하지 않는다.

        ```
        while c in counter:
            counter.remove(s[start])
            start += 1
        ```

      이 구조의 핵심은:

        - set을 새로 만들지 않는다
        - 필요한 문자만 제거한다
        - 각 문자는 최대 한 번 추가되고
        - 최대 한 번 제거된다

      즉 전체 시간복잡도는 O(n)

      ──────────────────────────────
      3️⃣ 시간복잡도 비교
      ──────────────────────────────

      네 코드:
        - popleft()
        - set(dq) 재생성 O(k)
        - 최악 O(n²)

      남 코드:
        - 각 문자 최대 1번 add
        - 각 문자 최대 1번 remove
        - 전체 O(n)

      ──────────────────────────────
      4️⃣ 메모리 및 객체 생성 비용
      ──────────────────────────────

      네 코드:
        - 매번 새 set 객체 생성
        - 해시 재계산
        - GC 비용 증가

      남 코드:
        - 같은 set 계속 사용
        - remove만 수행
        - 훨씬 효율적

      ──────────────────────────────
      5️⃣ 구조 차이의 본질
      ──────────────────────────────

      좋은 슬라이딩 윈도우는:

        ```
        윈도우를 "이동"시킨다
        ```

      비효율적인 슬라이딩 윈도우는:

        ```
        윈도우를 "재구성"한다
        ```

      네 코드는 윈도우를 계속 재구성하고 있고,
      남 코드는 윈도우를 이동만 한다.

      ──────────────────────────────
      🔥 핵심 한 줄 정리
      ──────────────────────────────

      set을 새로 만드는 순간 O(n²) 위험이 생긴다.

      슬라이딩 윈도우에서는
      절대 자료구조를 재생성하지 말고
      포인터만 이동시키는 것이 정석이다.