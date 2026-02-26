class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        d = dict()
        for s in strs:
            sorted_s = sorted(s)
            key = ''.join(sorted_s)

            if key in d:
                d[key].append(s)
            else:
                d[key] = [s]

        return list(d.values())
