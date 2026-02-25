class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        # 메인 로직
        answer = 0
        num_set = set(nums)

        for num in num_set:
            if num - 1 not in num_set:
                cnt = 1
                target = num + 1

                while target in num_set:
                    target += 1
                    cnt += 1

                answer = max(answer, cnt)

        return answer