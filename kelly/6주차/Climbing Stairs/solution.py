class Solution(object):
    def climbStairs(self, n):
				#✅ 목표 계단 수(n) 크기의 memo를 만들고 -1로 초기화한다.
        memo = [-1] * (n + 1)

        def dp(n):
						#✅ 현재 계단 순서(n)가 0번째 혹은 1번째일 경우, 1을 반환한다. (base case)
            if n == 0 or n == 1:
                return 1
						#✅ 현재 계단(n)에 도달하는 가짓수가 지정되어있지 않다면, (-1)
            if memo[n] == -1:
								#✅ n-1번째, n-2번째 계단에 대하여 재귀함수를 호출한다. (recurrence relation)
                memo[n] = dp(n - 1) + dp(n - 2)
						#✅ 현재 계단(n)에 도달하는 총 가짓수를 반환한다.
            return memo[n]

        return dp(n)