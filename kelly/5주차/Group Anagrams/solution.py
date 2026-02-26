from collections import defaultdict

class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        # 메인 로직
        groups = defaultdict(list)

        for s in strs:
            groups[tuple(sorted(s))].append(s)

        return list(groups.values())
