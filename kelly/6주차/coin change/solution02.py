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