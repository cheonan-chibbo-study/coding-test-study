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