class Solution:
    def isValid(self, s: str) -> bool:
        stack = []
        item = {"(": ")", "{": "}", "[": "]"}

        for c in s:
            if c in item:
                stack.append(item[c])
            else:
                if not stack or stack[-1] != c:
                    return False
                stack.pop()

        return not stack