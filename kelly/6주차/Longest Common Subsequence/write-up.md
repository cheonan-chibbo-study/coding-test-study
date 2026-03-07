## 👀 제한 시간 안에 어디까지 해냈는가?

제한 시간 `30분`안에 혼자서 문제를 풀지 못했다.

---

## 🧑‍🔬 문제 분석

문자열 2개가 주어졌을 때 두 문자열의 공통된 시퀀스 중 가장 긴 시퀀스의 길이를 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= text1.length, text2.length <= 1000`
- `text1` and `text2` consist of only lowercase English characters.

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

우선 완전 탐색을 사용해 모든 시퀀스를 구해서 저장 후 하나씩 비교하는 방법을 생각해봤는데, 문자열 각각의 최대 길이가 1,000이므로 완전 탐색 방식을 사용하기에는 무리하는 생각이 들었다.

다음은 DP로 해결할 수 있는지 점화식을 찾아봤는데 점화식을 유추하기 너무 어려웠다.

결국 다른 사람의 풀이를 참고했다.

### 찾아본 풀이

https://www.slideshare.net/slideshows/killingcamp-longest-common-subsequencepdf/266452784

이 때, `text1` 의 맨 앞에 있는 `a` 가 제외된 상황과, 포함된 상황에서 공통 부분문자열을 구해보면 다음과 같다.

문제의 요구사항은 두 문자열 사이에서 가장 긴 공통 부분 수열을 찾는 것이다.

예를 들어, "abczdgh"와 "aedfrh" 두 문자열이 있다면, 이들 사이에서 가장 긴 공통 부분 수열은 "adh"가 된다.

![Untitled](attachment:e072922e-860d-48db-a4ec-60bc3d19ee98:Untitled.png)

문제를 해결하기 위해서는 먼저 두 문자열을 하나씩 비교하면서 공통 부분 수열을 찾아야 한다. 이 과정에서는 두 문자열의 각 문자를 비교하면서 순서대로 공통 부분 수열을 찾는다.

이때, 문자들의 순서는 중요하지만, 중간에 건너뛰는 문자들이 있을 수 있다. 이게 “subsequence"이라고 불리는 이유다.

우리의 목표는 두 문자열에서 글자를 하나씩 비교하면서 공통 부분 수열을 찾는 것이다. 이를 위해 dp를 사용할 수 있다. 우리는 각 문자열의 각 위치에서 가능한 공통 부분 수열의 길이를 기록하는 테이블을 만든다.

이 표를 채우는 과정에서는 이전 위치의 정보를 활용한다.

예를 들어, 첫 번째 문자열의 `i`번째 위치와 두 번째 문자열의 `j`번째 위치에서의 글자를 비교할 때, 만약 두 글자가 같다면, 그 글자를 공통 부분 수열에 추가할 수 있다. 그리고 이전 위치에서 찾은 공통 부분 수열의 길이에 1을 더해서 현재 위치에서 공통 부분 수열의 길이를 업데이트할 수 있다.

이런 방식으로 각 문자열에서의 모든 위치에 대해 반복하면서 공통 부분 수열의 길이를 찾는다.

$$
F(n,m) = \Bigg\{^{\normalsize F(n-1,m-1) + 1,\ (\mathrm{text1}[n]\ =\ \mathrm{text2}[m])}_{\normalsize\mathrm{max}(F(n-1,m),\ F(n,m-1))\ (\mathrm{text1}[n]\ !=\ \mathrm{text2}[m])}

$$

![image.png](attachment:f0af8946-3585-43b7-aebc-e3d3ed5d58c6:image.png)

### 결론

- 위 점화식을 활용해서 풀이 코드를 작성하면 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 찾아본 풀이로 작성한 코드

**[ 다른 사람의 아이디어로 내가 작성해본 코드 ]**

```python
class Solution:
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        # 메서드
        def dp(i, j):
            if i < 0 or i >= text1_size or j < 0 or j >= text2_size:
                return 0

            if (i, j) not in memo:
                if text1[i] == text2[j]:
                    memo[(i, j)] = dp(i - 1, j - 1) + 1
                else:
                    memo[(i, j)] = max(dp(i, j - 1), dp(i - 1, j))

            return memo[(i, j)]
        
        # 메인 로직
        text1_size = len(text1)
        text2_size = len(text2)

        memo = {}
        memo[(0, 0)] = 1 if text1[0] == text2[0] else 0

        return dp(text1_size - 1, text2_size -1)
```

**[ 다른 사람의 코드 ]**

```python
class Solution:
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        # 메인 로직
        n = len(text1)
        m = len(text2)
        dp = [[0] * (m + 1) for _ in range(n + 1)]

        for i in range(1, n + 1):
            for j in range(1, m + 1):
                if text1[i - 1] == text2[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1] + 1
                else:
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

        return dp[n][m]
```

내 코드도 통과는 하지만 다른 사람의 코드가 훨씬 빠르다. 이유가 궁금해서 GPT한테 물어봤다.

- GPT 답변

  # 다른 사람의 LCS 코드가 Kelly 코드보다 빠른 이유

  핵심 이유는 **재귀 + dict memoization vs 반복문 + 배열 DP** 차이 때문이다.
    
  ---

  ## 1️⃣ Kelly 코드 (느린 이유)

  Kelly 코드 특징

    ```
    재귀 + memo(dict)
    ```

  ### 1. 재귀 호출 비용

    ```
    dp(i-1, j)
    dp(i, j-1)
    dp(i-1, j-1)
    ```

    - 함수 호출 스택이 생성된다.
    - Python에서는 함수 호출 비용이 꽤 큰 편이다.
    - 호출이 많아질수록 성능이 떨어진다.

    ---

  ### 2. dict 조회 비용

  Kelly 코드

    ```
    memo[(i, j)]
    ```

  이 과정에서 다음 작업이 발생한다.

    1. (i, j) 튜플 생성
    2. 해시 계산
    3. dict 조회

  이 과정은 **배열 접근보다 훨씬 느리다.**
    
  ---

  ### 3. 튜플 객체 생성 비용

    ```
    (i, j)
    ```

  이 튜플 객체가 **DP 계산할 때마다 계속 생성된다.**

  객체 생성 자체도 비용이 발생한다.
    
  ---

  ## 2️⃣ 다른 사람 코드 (빠른 이유)

  다른 사람 코드 특징

    ```
    반복문 + 2차원 배열
    ```

  ### 1. 함수 호출 없음

    ```
    for i in range(...)
    for j in range(...)
    ```

  단순 반복문이기 때문에

  재귀 호출 비용이 없다.
    
  ---

  ### 2. 배열 접근은 매우 빠름

    ```
    dp[i][j]
    ```

    - 단순 메모리 인덱스 접근
    - 해시 계산 없음
    - tuple 생성 없음

  그래서 매우 빠르다.
    
  ---

  ### 3. 객체 생성 없음

  Kelly 코드에서는

    ```
    (i, j)
    ```

  같은 tuple 객체가 계속 생성된다.

  하지만 배열 DP에서는 **추가 객체 생성이 없다.**
    
  ---

  ## 3️⃣ 시간복잡도는 동일

  두 코드 모두

    ```
    O(n × m)
    ```

  시간복잡도는 같다.

  하지만 **상수 시간(constant factor)** 차이가 크다.

  특히 Python에서는

    ```
    재귀 + dict
    ```

  보다

    ```
    반복문 + list
    ```

  가 훨씬 빠르다.
    
  ---

  ## 4️⃣ 속도 차이 원인 정리

  | 원인 | Kelly 코드 | 다른 코드 |
      | --- | --- | --- |
  | 함수 호출 | 많음 | 없음 |
  | 자료구조 | dict | list |
  | 객체 생성 | tuple 계속 생성 | 없음 |
  | 접근 방식 | hash lookup | array index |

  그래서 **반복문 기반 DP가 훨씬 빠르게 실행된다.**
    
  ---

  ## 5️⃣ 알고리즘 자체는 동일

  두 코드는 같은 점화식을 사용한다.

    ```
    if 문자 같음
        dp[i][j] = dp[i-1][j-1] + 1
    
    else
        dp[i][j] = max(dp[i-1][j], dp[i][j-1])
    ```

  차이는 구현 방식이다.

    ```
    Top-down  (재귀 + memoization)
    Bottom-up (반복문 DP)
    ```
    
  ---

  ✅ **한 줄 결론**

  다른 코드가 더 빠른 이유는

  **재귀 + dict 대신 반복문 + 배열을 사용했기 때문**이다.


---

## 🥰 배운점 & 느낀점

- 이제 DP가 좀 익숙해졌다고 생각했는데 아니었다…
- 그래도 좌절하지 말고 다시 열심히 해보자.