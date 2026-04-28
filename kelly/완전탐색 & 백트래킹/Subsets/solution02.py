from itertools import combinations

class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        # 메인 로직
        answer = [[]]
        for i in range(1, len(nums) + 1):
            answer.extend(combinations(nums, i))

        return answer