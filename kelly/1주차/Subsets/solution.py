class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        # 메서드
        def backTracking(result, temp, start):
            result.append(temp[:])

            for i in range(start, len(nums)):
                temp.append(nums[i])
                backTracking(result, temp, i + 1)
                temp.pop()

        # 메인 로직
        result = []
        backTracking(result, [], 0)

        return result