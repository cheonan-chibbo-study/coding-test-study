class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        # 메인 로직
        N = len(nums)
        dp = [0] * N
        dp[0] = nums[0]

        for i in range(1, N):
            dp[i] = max(nums[i], dp[i - 1] + nums[i])

        return max(dp)