class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        # 메인 로직
        nums_size = len(nums)
        dp = [1] * nums_size

        for i in range(1, nums_size):
            for j in range(i):
                if nums[i] > nums[j]:
                    dp[i] = max(dp[i], dp[j] + 1)

        return max(dp)