## 👀 제한 시간 안에 어디까지 해냈는가?

`13초`를 남기고 문제를 해결하는데 성공했다. 제출한 정답 코드는 다음과 같다.

```python
# 19:10 

from collections import deque

class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        # 전역 데이터
        
        # 메서드

        # 메인 로직
        if amount == 0:
            return 0
        
        visited = [False for _ in range(0, amount)]
        dq = deque([[amount, 0]])
        while dq:
            amount, coin_count = dq.popleft()
            for c in coins:
                new_amount = amount - c
                new_coin_count = coin_count + 1
                if new_amount == 0:
                    return new_coin_count
                elif new_amount < 0 or visited[new_amount]:
                    continue
                else:
                    dq.append([new_amount, new_coin_count])
                    visited[new_amount] = True
        
        return -1
        
```

- 풀이법 자체는 문제를 보자마자 떠올려서 코드 작성 + 테스트 케이스 통과까지 10분밖에 걸리지 않았지만 최종 제출 케이스에서 메모리 초과가 발생하여 시간을 많이 잡아먹었다.
- deque가 문제인가 싶어 list로 변경해보았지만 오히려 시간 초과가 발생했다.
- 13초를 남기고 이미 확인한 amount 케이스 부터는 탐색을 진행하지 않도록 visited를 사용했더니 최종 정답 처리를 받았다.

P & J 트레이닝

- Java로 처음 시도하여 `5분 36초`만에 문제를 해결했다.
- Python으로 2차 풀이를 시도하여 `3분 31초`만에 문제를 해결했다.

---

## 🧑‍🔬 문제 분석

거스름돈 amount와 현재 가지고 있는 동전 종류 리스트가 입력되면 거스름돈을 돌려줄 최소 동전 개수를 구해 반환하는 문제이다. 만약 현재 가지고 있는 동전으로 거스름돈을 모두 줄 수 없다면 -1을 반환한다.

문제에 주어진 제약 조건은 다음과 같다.

**Constraints:**

- `1 <= coins.length <= 12`
- `1 <= coins[i] <= 231 - 1`
- `0 <= amount <= 104`

---

## 🤔 풀이 고민

### 그리디

문제를 처음 봤을 때는 그리디를 떠올렸다. 최소 동전 개수를 반환하는 문제이기 때문에 금액이 큰 동전부터 최대한 반환하면 가장 적은 동전을 줄 수 있지 않을까 생각했기 때문이다. 하지만 이 문제는 그리디로 풀 수 없다. 주어지는 동전들의 서로 배수가 아니기 때문에 오히려 중간 크기의 동전만 건네주는게 더 적은 개수를 주는 상황도 발생하기 때문이다.

### BFS

이 문제는 BFS를 응용해서 해결할 수 있다. 만약 amount가 11이고 동전 종류가 [1, 5, 7]이면 처음 동전을 7로 선택했을 때, 5로 선택했을 때, 1로 선택했을 때 등의 케이스를 모두 큐에 넣으면서 발생할 수 있는 가능성들을 모두 탐색하다보면 amount가 0이되는 순간에 도달할 수 있으며 거스름돈이 0미만이 되는 순간은 탐색하지 않도록 조건을 설정하면 주어진 동전 종류로 거스름돈을 줄 수 있는지 여부도 체크할 수 있게 된다.

같은 원리라면 DFS도 가능할 수 있지만 BFS 특성상 가까운 케이스부터 탐색하기 때문에 현재 선택한 동전 개수 기준으로 오름차순 탐색을 할 수 있어 DFS보다 압도적으로 시간 복잡도 상으로 유리하기 때문에 이 문제를 BFS를 활용해 푸는게 좋다.

### 결론

- 주어지는 동전 종류가 서로 배수가 아닌 경우도 있어 그리디로는 풀 수 없다.
- BFS를 활용해서 문제를 해결할 수 있다. DFS는 특성상 이 문제에 적용하기에는 시간 효율성이 좋지 않아서 BFS를 선택하는게 합리적이다.

---

## 🏃 코드 작성 과정

결과적으로 30분안에 정답 코드를 작성하는데 성공했지만 중간에 발생한 메모리 초과를 해결하는데 아주 많은 시간이 걸려 아슬아슬하게 13초를 남기고 문제를 해결할 수 있었다.

### 틀린 부분 정정

처음 작성한 코드는 아래와 같은데 테스트 케이스는 모두 만족했지만 최종 채점 케이스에서 메모리 초과가 발생하였다.

```python
Input

[1,2,5]
100
```

```python
from collections import deque

class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        # 메인 로직
        if amount == 0:
            return 0
        
        dq = deque([[amount, 0]])
        while dq:
            amount, coin_count = dq.popleft()
            for c in coins:
                new_amount = amount - c
                new_coin_count = coin_count + 1
                if new_amount == 0:
                    return new_coin_count
                elif new_amount < 0:
                    continue
                else:
                    dq.append([new_amount, new_coin_count])
        
        return -1
        
```

메모리 부족이 발생한 이유는 amount가 주어진 동전 금액들에 비해 매우 클 경우 정말 무수히 많은 경우의 수가 발생하기 때문이다. 그 경우에 수를 위 코드는 모두 탐색하기 때문에 그 과정에서 큐에 너무 많은 경우의 수가 들어가 메모리 초과가 발생한 모양이다.

이를 해결하기 위해 처음에는 deque를 list로 변경해보았지만 이번에는 같은 케이스에서 시간 초과가 발생했다. 그리고 사실 list로 변경한다고 하여도 문제의 근본적인 원인이 당연히 해결되지 않았을 것이다.

- 혹시나 deque에 이차원 배열을 넣어서 그런게 아닐까 싶었지만 역시 근본적인 문제는 해결할 수 없는건 매한가지이고 이차원 배열을 넣지 않더라도 각 케이스의 동전 개수를 기록할 공간은 필요해서 똑같다.

결론적으로 이 문제를 해결하기 위해 이미 경험해 본 amount 케이스부터는 더이상 방문하지 않도록 visited 배열을 추가하였다.

```python
from collections import deque

class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        # 메인 로직
        if amount == 0:
            return 0
        
        visited = [False for _ in range(0, amount)]
        dq = deque([[amount, 0]])
        while dq:
            amount, coin_count = dq.popleft()
            for c in coins:
                new_amount = amount - c
                new_coin_count = coin_count + 1
                if new_amount == 0:
                    return new_coin_count
                elif new_amount < 0 or visited[new_amount]:
                    continue
                else:
                    dq.append([new_amount, new_coin_count])
                    visited[new_amount] = True
        
        return -1
        
```

- 동전을 1 → 2를 선택한 케이스와 2 → 1을 선택한 케이스는 사실상 같은 결과를 가지기 때문에 둘 중 하나만 탐색해도 상관없다. 따라서 이미 경험해본 amount 케이스는 불필요한 탐색이므로 이 경우의 수를 제거하기 위해 amount별로 visited를 기록했고 덕분에 메모리 부족을 해결하고 최종 정답 처리를 받았다.

### 최종 정답 코드 개선

GPT에게 부탁해 코드를 아래와 같이 더 깔끔하게 개선했다.

```python
from collections import deque

class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        if amount == 0:
            return 0

        visited = [False] * (amount + 1)
        queue = deque([(amount, 0)])
        visited[amount] = True

        while queue:
            remain, count = queue.popleft()

            for coin in coins:
                next_remain = remain - coin

                if next_remain == 0:
                    return count + 1

                if next_remain < 0 or visited[next_remain]:
                    continue

                visited[next_remain] = True
                queue.append((next_remain, count + 1))

        return -1
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    from collections import deque
    
    class Solution:
        def coinChange(self, coins: List[int], amount: int) -> int:
            dq = deque([(amount, 0)])
            visited = set()
    
            while dq:
                cur_amount, cur_count = dq.popleft()
    
                if cur_amount == 0:
                    return cur_count
                
                for coin in coins:
                    next_amount = cur_amount - coin
    
                    if next_amount < 0 or next_amount in visited:
                        continue
                    
                    dq.append((next_amount, cur_count + 1))
                    visited.add(next_amount)
            
            return -1
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        public int coinChange(int[] coins, int amount) {
            Deque<int[]> dq = new ArrayDeque<>();
            Set<Integer> visited = new HashSet<>();
            dq.offer(new int[]{amount, 0});
    
            while (!dq.isEmpty()) {
                int[] cur = dq.poll();
    
                if (cur[0] == 0) {
                    return cur[1];
                }
    
                for (int coin : coins) {
                    int nextAmount = cur[0] - coin;
    
                    if (nextAmount < 0 || visited.contains(nextAmount)) {
                        continue;
                    }
    
                    dq.offer(new int[]{nextAmount, cur[1] + 1});
                    visited.add(nextAmount);
                }
            }
    
            return -1;
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 드디어 30분안에 문제를 해결했다. 처음 메모리 부족이 발생했을 때 도저히 방법이 떠오르지 않아 시간이 8분 정도 남았을 때 솔직히 포기하고 찾아보려고 했는데 어쨌건 실전에서는 그럼 불합격이니 남은 시간동안 계속 고민하려고 노력했다. 그리고 정말 아슬아슬하게 방법이 떠올라 문제를 해결할 수 있었다.
- 역시 30분 제한을 걸고 푸는 연습을 계속 해야겠다고 느꼈다. 그리고 아무리 시간이 얼마 남지 않았어도 포기하지 않고 물고 늘어지는게 중요한거 같다.