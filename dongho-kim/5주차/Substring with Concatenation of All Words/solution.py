from itertools import permutations
from collections import defaultdict

class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        answer = []
        each_length = len(words[0])

        target = defaultdict(int)
        for word in words:
            target[word] += 1

        n = len(s)
        for l in range(n):
            r = l + (each_length * len(words))
            if r >= n + 1:
                break

            counter = defaultdict(int)
            for i in range(l, r, each_length):
                substr = s[i:i+each_length]
                counter[substr] += 1

            is_ok = True
            for k, v in target.items():
                if counter[k] != v:
                    is_ok = False
                    break

            if is_ok:
                answer.append(l)

        return answer
