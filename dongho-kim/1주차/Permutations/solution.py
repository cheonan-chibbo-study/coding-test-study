class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        answer = []
        used = [False] * len(nums)

        def recurse(result):
            if len(result) == len(nums):
                answer.append(result[:])
                return

            for i in range(len(nums)):
                if not used[i]:
                    used[i] = True
                    result.append(nums[i])
                    recurse(result)
                    result.pop()
                    used[i] = False

        recurse([])
        return answer
