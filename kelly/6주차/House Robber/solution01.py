class Solution:
    def rob(self, nums: List[int]) -> int:
        # 메서드
        def dp(n):
            if n not in costs:
                costs[n] = max(dp(n - 1), dp(n - 2) + nums[n])
            return costs[n]

        # 메인 로직
        N = len(nums)

        if N == 1:
            return nums[0]

        costs = {}
        costs[0] = nums[0]
        costs[1] = max(nums[0], nums[1])

        return dp(N - 1)