from itertools import permutations

class Solution:
    def getPermutation(self, n: int, k: int) -> str:
        candi = [list(p) for p in permutations([str(num) for num in range(1, n + 1)])]
        return ''.join(candi[k - 1])