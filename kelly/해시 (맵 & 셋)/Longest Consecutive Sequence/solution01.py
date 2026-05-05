class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        n_set = set(nums)

        answer = 0
        for n in n_set:
            if n - 1 not in n_set:
                cnt = 1
                target = n + 1

                while target in n_set:
                    cnt += 1
                    target += 1

                answer = max(answer, cnt)

        return answer