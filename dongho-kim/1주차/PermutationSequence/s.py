from itertools import permutations

class Solution:
    def getPermutation(self, n: int, k: int) -> str:
        arr = list(range(1, n+1))
        c = list(permutations(arr, n))
        result = c[k-1]
        return ''.join(map(str, result))
