## 👀 제한 시간 안에 어디까지 해냈는가?

`16분 11초`만에 혼자 문제를 해결할 수 있었다.

다만 내가 처음 작성한 코드가 매우 비효율적인 시간 복잡도를 가져서 이를 개선하기 위해 다른 사람의 풀이도 참고해봤다.

---

## 🧑‍🔬 문제 분석

정수 리스트가 주어지고 이 리스트의 서브 리스트 중 그 합이 가장 큰 값을 반환하는 문제이다.

문제에 주어지는 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= nums.length <= 105`
- `104 <= nums[i] <= 104`

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

처음 문제를 접했을 때 생각했던 방식은 완전 탐색이었다. 리스트에서 만들 수 있는 모든 서브 리스트의 합을 구해 그 중 최댓값을 반환하는 심플한 풀이법이다.

- 하지만 이 방법은 `O(N)` 이상의 시간 복잡도가 발생하는데 문제에 주어지는 배열의 최대 크기는 10^5이기 때문에 결코 `O(N^2)` 의 코드로 풀 수 없는 문제이다.
- 따라서 `O(N)`의 성능을 낼 수 있는 DP를 활용해야 하는 문제이다.

다행히 문제 조건이 까다롭지 않아 쉽게 점화식을 찾아낼 수 있었다. 내가 설계한 점화식은 다음과 같다.

```python
# 점화식
F(n) -> 입력 리스트의 n번째 값이 마지막 요소인 순열의 전체합 중 최대값
F(n) = max(input_list[n], F(n - 1) + input_list[n])

# base value
F(0) = input_list[0]
```

위 점화식을 활용해서 bottom-up 방식으로 코드를 작성해 제출하니 최종 정답 처리를 받을 수 있었다. 다만 시간 복잡도가 매우 비효율적이라 다른 사람의 풀이도 추가로 찾아봤다.

### 찾아본 풀이

다른 사람의 풀이도 내 점화식과 모두 비슷한 풀이를 사용했다. 다만 코드 작성에 사용된 자료구조와 정답 기록 방법이 달랐다.

- 내 코드와 달리 딕셔너리가 아니라 리스트를 사용했다.
- 내 코드처럼 매 반복문마다 answer를 갱신하는게 아니라 마지막에 dp 테이블의 최댓값을 찾아 반환했다.

위 요소들이 어떤 영향을 끼치길래 내 코드보다 빠른건지 GPT에게 물어봤다.

- 딕셔너리 보다 리스트를 사용한 방식이 더 빠른 이유

  좋은 질문이에요. Kelly 코드가 **논리적으로는 동일한 알고리즘(Kadane’s Algorithm)** 인데도 더 느린 이유는 **자료구조와 파이썬 연산 비용 차이** 때문입니다.

  핵심 차이를 하나씩 보면 이해가 됩니다.
    
  ---

  # 1️⃣ 가장 큰 차이: `list` vs `dict`

  ### 다른 사람 코드

    ```python
    dp = [0] * len(nums)
    ```

  ### Kelly 코드

    ```python
    dp = {0: nums[0]}
    ```

  여기서 성능 차이가 발생합니다.
    
  ---

  ## list 접근

    ```python
    dp[i]
    ```

  시간복잡도

    ```
    O(1)
    ```

  단순 **배열 인덱스 접근**입니다.
    
  ---

  ## dict 접근

    ```python
    dp[i]
    ```

  시간복잡도

    ```
    평균 O(1)
    ```

  하지만 실제로는 다음 작업이 들어갑니다.

    1. hash 계산
    2. hash table lookup
    3. key 비교

  즉 **상수 시간 비용이 훨씬 큽니다.**

  그래서 DP에서는 거의 항상

    ```
    list > dict
    ```

  입니다.
    
  ---

  # 2️⃣ 메모리 구조 차이

  ## list

    ```
    연속된 메모리
    ```

  CPU cache 친화적입니다.
    
  ---

  ## dict

    ```
    hash table
    ```

  메모리 여기저기 접근합니다.

  → CPU cache miss 발생

  그래서 반복문에서는 **차이가 크게 납니다.**
    
  ---

  # 3️⃣ 실제 연산 횟수 비교

  ### Kelly 코드

    ```python
    dp[i] = max(nums[i], dp[i - 1] + nums[i])
    ```

  여기서 발생하는 연산

    - dict lookup 2번
    - dict insert 1번

  즉

    ```
    hash 연산 3번
    ```

  이 발생합니다.
    
  ---

  ### 다른 사람 코드

    ```python
    dp[i] = max(nums[i], dp[i-1] + nums[i])
    ```

  여기서는

    - array read
    - array write

  즉

    ```
    단순 메모리 접근
    ```

  만 일어납니다.
    
  ---

  # 4️⃣ 더 중요한 사실 (이 문제의 진짜 최적 풀이)

  사실 이 문제는 **dp 배열도 필요 없습니다.**

  Kadane's Algorithm의 진짜 형태는 다음과 같습니다.

    ```python
    class Solution:
        def maxSubArray(self, nums):
            cur = nums[0]
            ans = nums[0]
    
            for i in range(1, len(nums)):
                cur = max(nums[i], cur + nums[i])
                ans = max(ans, cur)
    
            return ans
    ```

  공간복잡도

    ```
    O(1)
    ```

  입니다.
    
  ---

  # 5️⃣ 성능 순서

  이 문제 기준으로 보면

    ```
    1️⃣ O(1) Kadane (가장 빠름)
    2️⃣ list dp
    3️⃣ dict dp (Kelly 코드)
    ```

  입니다.
    
  ---

  # 6️⃣ Kelly 코드 자체는 좋은 점도 있음

  Kelly 코드의 장점 👍

    - 점화식 정확
    - DP 상태 정의 명확
    - answer 갱신 최적화

  즉 **알고리즘 사고 자체는 정확합니다.**

  단지

    ```
    DP 배열 → dict
    ```

  으로 바꾼 것이 성능을 떨어뜨린 것입니다.
    
  ---

  # ⭐ 코딩테스트 팁

  DP 문제에서 **인덱스 기반 상태**라면 거의 항상

    ```
    list 사용
    ```

  합니다.

  dict는 보통 다음과 같은 경우에 사용합니다.

    ```
    state가 sparse할 때
    ```

  예를 들어

    ```
    dp[(i, j)]
    dp[(mask, node)]
    dp[state]
    ```

  같은 경우입니다.
    
  ---

  # 💡 추가 참고

  이 **Maximum Subarray 문제는 DP로도 설명되지만 사실 Greedy로도 해석됩니다.**

  이 알고리즘은

    ```
    Kadane's Algorithm
    ```

  이며,

    ```
    DP + Greedy 성질을 동시에 가지는 알고리즘
    ```

  입니다.

- answer를 매 반복문마다 갱신하는 방법보다 마지막에 dp 테이블의 최대 값을 찾아 반환하는 방식이 더 빠른 이유

  좋은 질문입니다.

  겉보기에는 두 코드가 거의 동일하지만, 실제 실행에서는 **첫 번째 코드가 더 빠르게 나올 수 있습니다.**

  그 이유는 **루프 내부 연산 수 차이** 때문입니다.
    
  ---

  # 1️⃣ 두 코드의 차이

  ## 첫 번째 코드

    ```python
    for i in range(1, len(nums)):
        dp[i] = max(nums[i], dp[i - 1] + nums[i])
    
    return max(dp)
    ```

  루프 내부 연산

    - `max(nums[i], dp[i-1] + nums[i])`

  즉 **연산 1번**
    
  ---

  ## 두 번째 코드

    ```python
    for i in range(1, len(nums)):
        dp[i] = max(nums[i], dp[i - 1] + nums[i])
        answer = max(answer, dp[i])
    ```

  루프 내부 연산

    - `max(nums[i], dp[i-1] + nums[i])`
    - `max(answer, dp[i])`

  즉 **연산 2번**
    
  ---

  # 2️⃣ 연산 횟수 비교

  배열 길이를 `n`이라고 하면

  ### 첫 번째 코드

  루프

    ```
    n - 1 번
    ```

  루프 내부 연산

    ```
    max 연산 1번
    ```

  총

    ```
    (n - 1) 번
    ```

  그리고 마지막에

    ```
    max(dp)
    ```

  이 수행됩니다.

  `max(dp)` 시간복잡도

    ```
    O(n)
    ```
    
  ---

  ### 두 번째 코드

  루프

    ```
    n - 1 번
    ```

  루프 내부 연산

    ```
    max 연산 2번
    ```

  총

    ```
    2(n - 1)
    ```
    
  ---

  # 3️⃣ 왜 첫 번째 코드가 더 빠를 수 있나

  파이썬에서는

    ```
    max(dp)
    ```

  같은 **C로 구현된 내장 함수**는 매우 빠르게 실행됩니다.

  즉

    ```
    한 번의 O(n) 연산
    ```

  보다

    ```
    루프 내부에서 n번 실행되는 Python 연산
    ```

  이 더 느릴 수 있습니다.

  그래서 실제 실행 시간에서는

    ```
    첫 번째 코드 > 두 번째 코드
    ```

  가 되는 경우가 있습니다.
    
  ---

  # 4️⃣ 실제로는 더 빠른 풀이가 있음

  이 문제는 **Kadane's Algorithm**이라서

  DP 배열도 필요 없습니다.

    ```python
    class Solution:
        def maxSubArray(self, nums):
            cur = nums[0]
            ans = nums[0]
    
            for i in range(1, len(nums)):
                cur = max(nums[i], cur + nums[i])
                ans = max(ans, cur)
    
            return ans
    ```

  시간복잡도

    ```
    O(n)
    ```

  공간복잡도

    ```
    O(1)
    ```
    
  ---

  # 5️⃣ 정리

  두 코드의 차이

    ```
    첫 번째 코드
    루프 내부 max 1번
    + 마지막 max(dp)
    
    두 번째 코드
    루프 내부 max 2번
    ```

  파이썬에서는

    ```
    C로 구현된 max(dp)
    ```

  가

    ```
    Python 루프 내부 연산
    ```

  보다 빠른 경우가 있어서

  첫 번째 코드가 더 빠르게 측정될 수 있습니다.


### 결론

- 내가 설계한 점화식을 활용해 bottom-up 방식으로 풀이를 작성하면 문제를 해결할 수 있다.
- 다만 리스트 사용 + 매 반복문마다 정답 갱신 코드 때문에 다른 사람의 풀이보다 좀 느렸다. 이 부분은 개선할 필요가 있었다.

---

## 🏃 코드 작성 과정

### 내가 처음 작성한 코드

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        # 메인 로직
        answer = nums[0]
        dp = {0: nums[0]}

        for i in range(1, len(nums)):
            dp[i] = max(nums[i], dp[i - 1] + nums[i])
            answer = max(answer, dp[i])
        
        return answer
```

### 찾아본 풀이로 작성한 코드

내가 처음 작성한 풀이와 점화식 자체는 같고, 비효율적인 자료구조 + 방식만 개선했다.

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        # 메인 로직
        N = len(nums)
        dp = [0] * N
        dp[0] = nums[0]

        for i in range(1, N):
            dp[i] = max(nums[i], dp[i - 1] + nums[i])
        
        return max(dp)
```

---

## 🥰 배운점 & 느낀점

- 이번에는 혼자서 빠르게 점화식을 세우고 정답 코드를 작성할 수 있어 뿌듯했다.
- 다만 작성한 코드의 시간 복잡도가 낮아서 아쉬웠다. 이 부분을 보완할 수 있도록 이 페이지의 내용을 복습하자.
- 점화식이 바로 나오면 bottom-up으로 바로 코드를 작성하는게 편한거 같다.