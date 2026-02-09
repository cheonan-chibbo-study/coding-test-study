class Solution:
    def trap(self, height: List[int]) -> int:
        max_height = max(height)
        max_height_idx = [i for i, h in enumerate(height) if h == max_height][0]

        answer = 0

        stack = []
        for i, x in enumerate(height):
            if i == max_height_idx:
                break

            if i == 0:
                stack.append(x)
                continue

            if stack and stack[-1] > x:
                answer += (stack[-1] - x)
                continue

            if stack and stack[-1] < x:
                stack.append(x)

        stack.clear()
        for i, x in list(enumerate(height))[::-1]:
            if i == max_height_idx:
                break

            if i == len(height)-1:
                stack.append(x)
                continue

            if stack and stack[-1] > x:
                answer += (stack[-1] - x)
                continue

            if stack and stack[-1] < x:
                stack.append(x)

        return answer
