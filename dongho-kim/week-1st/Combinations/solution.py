class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        answer = []
        used = [False] * (n+1)

        def recurse(start: int, result: List[int]):
            if len(result) == k:
                answer.append(result[:])
                return

            for num in range(start, n+1):
                if used[num]:
                    continue
                used[num] = True
                result.append(num)
                recurse(num+1, result)
                result.pop()
                used[num] = False

        recurse(1, [])
        return answer


