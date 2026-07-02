class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        memo = {0: 0, 1: 0}

        # 메서드
        def dp(n):
            if n not in memo:
                memo[n] = min(dp(n - 1) + cost[n - 1], dp(n - 2) + cost[n - 2])

            return memo[n]

        # 메인 로직
        return dp(len(cost))