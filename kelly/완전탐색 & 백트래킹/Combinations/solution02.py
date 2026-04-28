from itertools import combinations

class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        # 메인 로직
        candi = [i for i in range(1, n + 1)]
        combi_list = combinations(candi, k)
        return [list(combi) for combi in combi_list]