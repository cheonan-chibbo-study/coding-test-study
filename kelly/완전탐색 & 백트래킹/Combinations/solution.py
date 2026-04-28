class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        # 메서드
        def recursive(temp, result, start):
            if len(temp) == k:
                result.append(temp[::])
                return

            for i in range(start, n + 1):
                temp.append(i)
                recursive(temp, result, i + 1)
                temp.pop()

        # 메인 로직
        answer = []
        recursive([], answer, 1)
        return answer