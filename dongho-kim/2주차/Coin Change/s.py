from collections import deque

class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        if amount == 0:
            return 0

        distance = [0] * (10 ** 4 + 1)
        self.bfs(0, coins, distance, amount)

        if distance[amount] == 0:
            return -1
        return distance[amount]

    def bfs(self, i, coins, distance, target):
        q = deque()
        q.append(i)

        while q:
            curr = q.popleft()

            if curr == target:
                break

            for coin in coins:
                nxt = curr + coin

                if nxt >= len(distance):
                    continue

                if distance[nxt] == 0:
                    distance[nxt] = distance[curr] + 1
                    q.append(nxt)
