class Solution:
    def longestValidParentheses(self, s: str) -> int:
        stack = []
        matched = [False] * len(s)

        for i, c in enumerate(s):
            if c == '(':
                stack.append(i)
                continue

            if c == ')':
                if len(stack) == 0:
                    continue

                prev_idx = stack.pop()

                matched[prev_idx] = True
                matched[i] = True

        answer = 0
        curr = 0
        for m in matched:
            if m:
                curr += 1
                continue

            answer = max(answer, curr)
            curr = 0
        answer = max(answer, curr)

        return answer
