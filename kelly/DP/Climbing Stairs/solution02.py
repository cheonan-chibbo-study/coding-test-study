class Solution:
    def climbStairs(self, n: int) -> int:
        dp = [None] * (n + 1)
        dp[0] = 1
        dp[1] = 1

        #✅ 2번째 계단부터 n번째 계단까지 올라간다.
        for i in range(2, n + 1):
            #✅ 점화식에 따라 각 계단에 도달할 수 있는 총 가짓수를 구한다.
            dp[i] = dp[i - 1] + dp[i - 2]

        #✅ 현재 계단(n)에 도달하는 총 가짓수를 반환한다.
        return dp[n]