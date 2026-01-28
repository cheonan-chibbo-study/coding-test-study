class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        # 메서드
        def backTracking(result, temp, start):
            if len(temp) == k:
                result.append(temp[:])
                return
            
            for i in range(start, n + 1):
                temp.append(i)
                backTracking(result, temp, i + 1)
                temp.pop()

        # 메인 로직
        result = []
        backTracking(result, [], 1)

        return result