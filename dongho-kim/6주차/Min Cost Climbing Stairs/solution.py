"""
# 요구사항
각 계단의 칸을 오르는 비용을 나타내는 cost 정수 배열이 주어진다.
시작 지점은 0 또는 1에서 시작할 수 있을 때, 최소한의 비용으로 층의 맨 위까지 도달하라.

# 접근 방법
1. 0이랑 1에서 각각 시작해보고, 계단을 올라갈 때는 1칸 또는 2칸을 올라가본다.
"""
class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        n = len(cost)

        memo = [1e9] * n
        def recurse(curr):
            if curr > n:
                return 1e9
            if curr == n:
                return 0
            if memo[curr] != 1e9:
                return memo[curr]

            result = 1e9
            result = min(result, recurse(curr + 1) + cost[curr])
            result = min(result, recurse(curr + 2) + cost[curr])
            memo[curr] = result
            return result

        return min(recurse(0), recurse(1))
