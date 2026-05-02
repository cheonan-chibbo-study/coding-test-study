class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        answer = [0] * len(temperatures)
        stack = []

        for day in range(len(temperatures)):
            if not stack:
                stack.append((day, temperatures[day]))
                continue

            while stack and stack[-1][1] < temperatures[day]:
                popped = stack.pop()
                answer[popped[0]] = day - popped[0]

            stack.append((day, temperatures[day]))

        return answer