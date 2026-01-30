class Solution:
    answer = -1

    def search(self, nums: List[int], target: int) -> int:
        l = 0
        r = len(nums) - 1

        while l <= r:
            mid = (l + r) // 2
            mid_val = nums[mid]
            if mid_val == target:
                self.answer = mid
                break
            if mid_val < target:
                l = mid + 1
                continue
            if mid_val > target:
                r = mid - 1

        return self.answer
