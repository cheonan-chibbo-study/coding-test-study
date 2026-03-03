class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        n = len(cost)
				#✅ memo를 만든다.
        memo = {}

        def dp(n):
						#✅ 0번째 계단 혹은 1번째 계단일 경우, 0을 반환한다. (base case)
            if n == 0 or n == 1:
                return 0
						#✅ 현재 계단(n)까지의 도달에 필요한 비용이 memo에 없다면,
            if n not in memo:
								#✅ 점화식에 따라 재귀함수를 호출하여 n번째 계단에 대한 최소 비용을 구한다. (recurrence relation)
                memo[n] = min(dp(n - 1) + cost[n - 1], dp(n - 2) + cost[n - 2])
						#✅ 현재 계단(n)까지의 도달에 필요한 최소 비용을 반환한다.
            return memo[n]

        return dp(n)