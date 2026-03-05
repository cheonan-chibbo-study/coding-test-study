class Solution:
    def rob(self, nums: List[int]) -> int:
        # 메인 로직
        N = len(nums)

        if N == 1:
            return nums[0]

        costs = {}
        costs[0] = nums[0]
        costs[1] = max(nums[0], nums[1])

        for i in range(2, N):
            costs[i] = max(costs[i - 1], costs[i - 2] + nums[i])

        return costs[N - 1]