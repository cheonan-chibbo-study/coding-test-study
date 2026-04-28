class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        # 메서드
        def recursive(temp, result, start):
            result.append(temp[::])

            if len(temp) == len(nums):
                return

            for i in range(start, len(nums)):
                temp.append(nums[i])
                recursive(temp, result, i + 1)
                temp.pop()

        # 메인 로직
        answer = []
        recursive([], answer, 0)
        return answer