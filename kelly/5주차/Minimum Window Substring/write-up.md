## 👀 제한 시간 안에 어디까지 해냈는가?

제한시간 `30분`안에 문제를 풀지 못했다. 이후 동호님의 아이디어를 듣고 집에서 혼자 구현하는데 성공했다.

아래는 내가 혼자 집에서 구현한 코드이다.

```python
from collections import deque, defaultdict

class Solution:
    def minWindow(self, s: str, t: str) -> str:
        # 메서드
        def check():
            for k, v in target.items():
                if counter[k] < v:
                    return False
            return True
        
        # 메인 로직
        target_set = set(list(t))
        target = defaultdict(int)
        for c in t:
            target[c] += 1

        l, r = 0, 0
        counter = defaultdict(int)
        counter[s[0]] = 1
        dq = deque()
        answer = ""

        while l <= r:
            # 현재 윈도우에 target 요소가 모두 포함됨
            if check():
                if not answer or len(answer) > r - l + 1:
                    answer = s[l:r + 1]

                next_l = dq.popleft() if dq else l + 1

                if next_l >= len(s):
                    break
                
                for idx in range(l, next_l):
                    counter[s[idx]] -= 1
                
                l = next_l

            else:
                r += 1
                if r >= len(s):
                    break
                elif s[r] in target_set:
                    dq.append(r)

                counter[s[r]] += 1
        
        return answer
```

---

## 🧑‍🔬 문제 분석

문자열 s, t가 주어졌을 때 t의 각 요소를 순서 상관없이 모두 포함하는 s의 부분 문자열 중 가장 길이가 짧은 문자열을 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `m == s.length`
- `n == t.length`
- `1 <= m, n <= 105`
- `s` and `t` consist of uppercase and lowercase English letters.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

제한 시간을 걸고 문제를 풀때는 s와 t의 최대 길이를 보고 적어도 `O(N)`의 시간내로 동작하는 코드를 작성해야한다는걸 깨닫고 여러 아이디어를 고민했다. 개념을 잘 몰랐지만 어찌어찌 슬라이딩 윈도우를 떠올려서 코드를 작성해보려 했지만 문제는 특정 윈도우에 t 요소가 포함되어 있는지 검사하는 로직을 어떻게 작성해야할지 감이 오지 않아 결국 혼자서 코드를 작성하지 못했다.

- t도 s와 마찬가지로 최대 10^5의 길이 문자열이 들어올 수 있는데 매번 t in 현재 윈도우 코드를 작성하면 시간 복잡도상으로 O(N^2)이 걸리지 않나 헷갈렸다.
- 그렇다고 t를 set으로 구성하기에는 문제 세번재 케이스처럼 “aa”는 a가 2번 포함되어 있어야 하는 경우도 있어서 set을 사용할 수 없었다.

이후 동호님의 풀이를 듣고 이 문제를 해결할 아이디어를 떠올릴 수 있었다.

- 코드 초반에 t를 순회하면서 t에 포함된 문자의 개수를 각각 딕셔너리에 저장한다.
- 이후 특정 윈도우에 t 요소가 포함되어 있는지 검사하는 코드는 t 딕셔너리를 순회해서 t의 각 문자 개수보다 윈도우 내부의 각 문자 개수가 같거나 큰지를 검사하면 된다.
- 이 과정에서 비교하는 주체는 결국 알파벳이기 때문에 최대 26번만 순회가 발생해서 사실상 `O(1)`의 시간으로 동작하므로 매번 반복문에서 이 코드가 실행되더라도 큰 부담이 없다.

따라서 슬라이딩 윈도우 + 동호님의 풀이를 통해 발견한 아이디어를 접목해 풀이 코드를 작성해 제출했고 최종 정답 처리를 받을 수 있었다.

다만 내가 작성한 풀이는 제법 낮은 통과 시간을 보였기 때문에 더 최적화된 풀이가 없을지 찾아보기로 했다.

### 찾아본 풀이

아래 블로그 풀이가 직관적이기도 하고 이 풀이로 작성한 코드가 내가 처음 작성한 코드보다 훨씬 빠른 시간으로 통과되어서 이 풀이를 참고하기로 결정했다. 자세한 풀이는 블로그를 참고하자.

[76. Minimum Window Substring](https://velog.io/@ysinfrance/76.-Minimum-Window-Substring)

### 결론

- 슬라이딩 윈도우 + 각 알파벳 개수 비교를 통해 문제를 해결할 수 있다.
- 내가 처음 생각한 풀이는 속도가 느리므로 찾아본 블로그 풀이를 참고해 최적화된 코드도 공부하자.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

```python
from collections import deque, defaultdict

class Solution:
    def minWindow(self, s: str, t: str) -> str:
        # 메서드
        def check():
            for k, v in target.items():
                if counter[k] < v:
                    return False
            return True
        
        # 메인 로직
        target_set = set(list(t))
        target = defaultdict(int)
        for c in t:
            target[c] += 1

        l, r = 0, 0
        counter = defaultdict(int)
        counter[s[0]] = 1
        dq = deque()
        answer = ""

        while l <= r:
            # 현재 윈도우에 target 요소가 모두 포함됨
            if check():
                if not answer or len(answer) > r - l + 1:
                    answer = s[l:r + 1]

                next_l = dq.popleft() if dq else l + 1

                if next_l >= len(s):
                    break
                
                for idx in range(l, next_l):
                    counter[s[idx]] -= 1
                
                l = next_l

            else:
                r += 1
                if r >= len(s):
                    break
                elif s[r] in target_set:
                    dq.append(r)

                counter[s[r]] += 1
        
        return answer
```

### 찾아본 풀이로 작성한 코드

```python
from collections import defaultdict

class Solution:
    def minWindow(self, s: str, t: str) -> str:
        # T에 있는 각 문자의 등장 횟수를 저장하는 해시 맵
        target = defaultdict(int)
        for c in t:
            target[c] += 1
        
        # 슬라이딩 윈도우 설정을 위한 포인터 및 결과 변수
        l, r = 0, 0
        min_len = float('inf')
        answer = ""
        required_chars = len(t)

        # right 포인터가 문자열 s의 끝에 도달할 때까지 아래의 과정을 반복합니다.
        while r < len(s):
            # 현재 문자를 윈도우에 추가
            # 현재 right가 가리키는 문자가 t에 속한다면, 해당 문자의 등장 횟수를 감소시키고, 
            # 필요한 문자의 수도 갱신합니다.
            if s[r] in target:
                target[s[r]] -= 1
                if target[s[r]] >= 0:
                    required_chars -= 1
                
            # 모든 문자가 포함된 경우
            while required_chars == 0:
                # 최소 윈도우 갱신
                if r - l + 1 < min_len:
                    min_len = r - l + 1
                    answer = s[l:r+1]
                # left를 이동하여 최소 윈도우 축소:
                if s[l] in target:
                    target[s[l]] += 1
                    if target[s[l]] > 0:
                        required_chars += 1
                l += 1
            # right를 이동하여 윈도우 확장
            r += 1

        return answer
```

- 이 코드가 내가 처음 작성한 코드보다 더 빠른 실행 속도를 보인다. 왜 이 코드가 더 빠른지 GPT에게 물어봤다.
    - GPT 답변

      [ 왜 두 번째 코드가 더 빠른가? ]

      핵심 차이: "check() 반복 검증 제거" + "O(1) 상태 추적"

      ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
      1️⃣ 첫 번째 코드의 느린 이유
      ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

      ✔ 매번 check() 호출
      def check():
      for k, v in target.items():
      if counter[k] < v:
      return False

      → target의 모든 문자를 매번 순회함
      → target 길이를 M이라 하면
      → check() 한 번에 O(M)

      그리고 while 루프 안에서 반복 호출됨

      결과적으로:
      O(N * M)

      t 길이가 길어질수록 계속 느려짐

      ✔ counter를 직접 줄이는 반복문 존재

      for idx in range(l, next_l):
      counter[s[idx]] -= 1

      → 최악의 경우 여러 번 반복
      → 윈도우 축소할 때마다 비용 발생

      ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
      2️⃣ 두 번째 코드가 빠른 이유
      ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

      ✔ check() 자체를 제거

      대신:

      required_chars 변수를 사용해
      "조건 만족 여부"를 O(1)로 추적

      if target[s[r]] >= 0:
      required_chars -= 1

      → 필요한 문자 수를 즉시 갱신

      조건 확인도:

      while required_chars == 0:

      → O(1)

      ✔ 포인터는 각각 한 방향으로만 이동

      l: 최대 N번 이동
      r: 최대 N번 이동

      → 전체 시간복잡도 O(N)

      ✔ counter를 한 칸씩만 조정

      target[s[l]] += 1

      → 대량 반복 감소 없음

      ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
      3️⃣ 시간복잡도 비교
      ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

      첫 번째 코드:
      O(N * M)

      두 번째 코드:
      O(N)

      ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
      4️⃣ 근본적인 차이
      ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

      첫 번째 코드:
      "현재 윈도우가 조건을 만족하는지 매번 검사"

      두 번째 코드:
      "조건 만족 여부를 실시간으로 추적"

      즉,

      ❌ 상태를 매번 다시 계산
      vs
      ✅ 상태를 유지하면서 업데이트

      ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
      한 줄 요약:

      두 번째 코드는
      '검사형 알고리즘'이 아니라
      '상태 유지형 알고리즘'이기 때문에
      더 빠르다.


---

## 🥰 배운점 & 느낀점

- 슬라이딩 윈도우를 개념만 알고 있었는데 이번 기회에 체화할 수 있어서 기분이 좋다.
- 다른 사람의 아이디어나 최적화 코드는 늘 신기한거 같다. 쓸만한 내용을 잘 기록해서 복습하고 나중에 잘 써먹어야겠다.