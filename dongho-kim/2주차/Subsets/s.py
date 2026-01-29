class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        answer = []

        def recurse(target_size: int, start_index: int, result: List[int]):
            if len(result) == target_size:
                answer.append(result[:])
                return

            for i in range(start_index, len(nums)):
                if len(result) < target_size:
                    result.append(nums[i])
                    recurse(size, i + 1, result)
                    result.pop()

        for size in range(0, len(nums) + 1):
            recurse(size, 0, [])
        return answer
