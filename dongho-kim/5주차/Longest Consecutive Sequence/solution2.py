class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        answer = 0

        num_set = set(nums)
        for num in num_set:
            # 시작점인 경우
            if (num - 1) not in num_set:
                curr = num
                length = 1

                while (curr + 1) in num_set:
                    curr += 1
                    length += 1

                answer = max(answer, length)

        return answer
