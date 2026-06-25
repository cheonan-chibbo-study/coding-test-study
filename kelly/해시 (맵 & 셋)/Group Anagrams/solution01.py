from collections import defaultdict

class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        graph = defaultdict(list)

        for v in strs:
            graph[''.join(sorted(v))].append(v)

        return [v for v in graph.values()]