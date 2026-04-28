from itertools import permutations

class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        # 메인 로직
        return [p for p in permutations(nums)]