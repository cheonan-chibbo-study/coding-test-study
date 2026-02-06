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