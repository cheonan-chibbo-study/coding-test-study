class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        # 메서드
        def recursive(temp, result):
            if len(temp) == len(nums):
                result.append(temp[::])
                return

            for n in nums:
                if n in temp:
                    continue

                temp.append(n)
                recursive(temp, result)
                temp.pop()

        # 메인 로직
        answer = []
        recursive([], answer)

        return answer