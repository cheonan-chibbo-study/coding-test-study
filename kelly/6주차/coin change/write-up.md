## 👀 제한 시간 안에 어디까지 해냈는가?

제한 시간 `30분`안에 혼자 문제를 풀지 못했다. 혼자 점화식을 세워보기는 했지만 이전까지 살펴본 점화식과 살짝 다른 형태의 점화식이 나왔기 때문에 코드 구현을 하지 못했다.

---

## 🧑‍🔬 문제 분석

거스름 돈 금액과 동전 종류 리스트가 주어졌을 때 동전을 무한히 사용해서 거스름 돈을 거슬러 줄 수 있는 최소 동전 개수를 반환하는 문제이다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= coins.length <= 12`
- `1 <= coins[i] <= 231 - 1`
- `0 <= amount <= 104`

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

우선 혼자서 점화식을 아래와 같이 세워봤다.

```python
F(n) -> n원을 거슬러 줄 수 있는 최소 동전 개수
m -> coins 개수

F(n) = min(F(n - coins[0]) + 1, F(n - coins[1]) + 1, …, F(n - coins[m - 1]) + 1)

F(0) = 0
F(coins[0]) = 1
F(coins[1]) = 1
…
F(coins[m - 1]) = 1
```

다만 이전까지 살펴본 점화식과 다르게 min() 함수 내부에 입력 값에 따른 유동적인 파라미터 개수가 들어가는 점화식이 세워져서 이를 어떻게 코드로 구현해야할지 감을 잡지 못해 다른 사람의 풀이도 살펴봤다.

그래도 다행히 내가 세운 점화식이 방향 자체는 올바른 점화식이었다.

### 찾아본 풀이

특정 금액을 만드는 방법은 해당 금액에서 각 동전의 비용만큼 적은 금액에서 해당 동전을 선택하는 것이다. 문제에서 요구하는 값은 해당 금액을 만들기 위한 동전의 조합 중 동전의 개수가 최소가 되는 값이며 위에서 설명한 특정 금액을 만드는 경우의 수 중 동전의 개수가 최소인 경우를 골라야 한다.

그렇기에 다음과 같은 점화식을 유도할 수 있다.

$$
F(n) = \mathrm{min}(…\mathrm{F}(n-\mathrm{coins[i]}))+1\\F(0) = 0
$$

해당 점화식을 통해 top-down, bottom-up 방식으로 코드를 구현할 수 있는데 이 때 중복되는 값을 구하는 소요가 생기며 이를 memoization을 통한 중복 제거로 최적화 할 수 있다.

### 결론

- 혼자서는 점화식 설계까지만 할 수 있었다. 그래도 혼자서 설계한 점화식이 올바른 방향이었다.
- 위 점화식(DP)을 활용하면 top-down & bottom-up 방식으로 풀이 코드를 작성해 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 찾아본 풀이로 작성한 코드

**[ top-down ]**

```python
class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        # 메서드
        def dp(n):
            if n == 0:
                return 0
            
            candi_list = []
            for coin in coins:
                if n - coin >= 0:
                    if n - coin not in memo:
                        memo[n - coin] = dp(n - coin)
                    if memo[n - coin] != -1:
                        candi_list.append(memo[n - coin])
            
            return min(candi_list) + 1 if candi_list else -1

        # 메인 로직
        memo = {}
        return dp(amount)
```

**[ bottom-up ]**

```python
class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        # 메인 로직
        dp = [float('inf')] * (amount + 1)
        dp[0] = 0

        for i in range(amount + 1):
            for coin in coins:
                if i + coin <= amount:
                    dp[i + coin] = min(dp[i + coin], dp[i] + 1)
        
        return dp[amount] if dp[amount] != float('inf') else -1
```

---

## 🥰 배운점 & 느낀점

- 이번 문제는 혼자서 푸는데 실패했지만 그래도 혼자서 올바른 방향의 점화식을 설계하는건 성공했다.
- 앞선 문제들과 함깨 이 문제도 복습하면 간단한 흐름의 점화식은 이제 혼자서도 설계할 수 있을거 같다.