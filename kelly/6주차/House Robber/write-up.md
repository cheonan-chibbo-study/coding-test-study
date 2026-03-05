## 👀 제한 시간 안에 어디까지 해냈는가?

제한 시간 `30분`안에 혼자서 문제를 풀지 못했다. 결국 다른 사람의 풀이를 참고했다.

---

## 🧑‍🔬 문제 분석

각 집에서 얻을 수 있는 비용들이 리스트로 주어졌을 때, 인접한 집들을 제외하고 털었을 때 가장 많이 털 수 있는 비용을 반환하는 문제이다.

- 자세한 조건은 문제를 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= nums.length <= 100`
- `0 <= nums[i] <= 400`

---

## 🤔 풀이 고민

### 혼자서 생각해낸 풀이

혼자서 점화식을 떠올리려고 노력했지만 점화식이 마땅히 떠오르지 않아 결국 다른 사람의 풀이를 참고했다.

### 찾아본 풀이

$n$번째 집까지 훔칠 때의 최대 금액을 생각해보자.

- 만약 $n$번째 집을 훔치지 않는다면 $n-1$번째 집을 훔쳤을 때의 최댓값과 같고,
- $n$번째 집을 훔친다면 바로 이전 집은 훔칠 수 없으므로 $n-2$번째 집을 훔쳤을 때의 최댓값에 $n$번째 집의 금액을 더한 것이 된다.

$F(n)$을 $n$번째 집까지 털었을 때의 최댓값이라고 한다면 다음 점화식이 성립한다.

$$
F(n) = \mathrm{max}(F(n-1), F(n-2) + \mathrm{cost}(n))\\F(0) = \mathrm{cost}(0)\\F(1) = \mathrm{max}(\mathrm{cost}(0),\ \mathrm{cost}(1))
$$

이제 이 점화식을 top down 혹은 bottom up 방식으로 구현해주면 된다.

### 결론

- 위 점화식(DP)을 활용하면 top-down & bottom-up 방식으로 문제를 해결할 수 있다.

---

## 🏃 코드 작성 과정

### 찾아본 풀이로 작성한 코드

위 풀이를 코드로 구현하면 다음과 같다.

**[ top-down ]**

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        # 메서드
        def dp(n):
            if n not in costs:
                costs[n] = max(dp(n - 1), dp(n - 2) + nums[n])
            return costs[n]

        # 메인 로직
        N = len(nums)

        if N == 1:
            return nums[0]

        costs = {}
        costs[0] = nums[0]
        costs[1] = max(nums[0], nums[1])

        return dp(N - 1)
```

**[ bottom-up ]**

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        # 메인 로직
        N = len(nums)

        if N == 1:
            return nums[0]

        costs = {}
        costs[0] = nums[0]
        costs[1] = max(nums[0], nums[1])

        for i in range(2, N):
            costs[i] = max(costs[i - 1], costs[i - 2] + nums[i])
        
        return costs[N - 1]
```

---

## 🥰 배운점 & 느낀점

- 처음에는 어떻게 접근해야 좋을지 감이 잡히지 않았는데 막상 다른 사람의 설명을 보면 바로 이해가 되서 허무한거 같다.
- 점화식 세우는건 정말 많은 연습이 필요하다고 했으니 노력하자…