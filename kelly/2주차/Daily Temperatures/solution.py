class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        result = [0] * len(temperatures)
        stack = []  # 날짜 index만 저장

        for d, t in enumerate(temperatures):
            while stack and temperatures[stack[-1]] < t:
                prev_day = stack.pop()
                result[prev_day] = d - prev_day
            stack.append(d)

        return result
