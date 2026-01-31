class Solution:
    def search(self, nums: List[int], target: int) -> int:
        # 메서드
        def binarySearch(left, right):
            if left > right:
                return -1
            
            mid = left + ((right - left) // 2)
            if nums[mid] == target:
                return mid
            elif nums[mid] < target:
                left = mid + 1
            else:
                right = mid - 1
                
            return binarySearch(left, right)
            
        # 메인 로직
        return binarySearch(0, len(nums) - 1)