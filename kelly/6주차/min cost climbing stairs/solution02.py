class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        # 전역 데이터
        n = len(cost)

        costs = [-1] * (n + 1)
        costs[0] = 0
        costs[1] = 0

        # 메인 로직
        for i in range(2, n + 1):
            costs[i] = min(costs[i - 1] + cost[i - 1], costs[i - 2] + cost[i - 2])

        return costs[n]