class Solution:
    def isValid(self, s: str) -> bool:
        stack = []
        for c in s:
            if c == '(' or c == '{' or c == '[':
                stack.append(c)
                continue

            if len(stack) == 0:
                return False

            if c == ')' and stack[-1] != '(':
                return False
            if c == '}' and stack[-1] != '{':
                return False
            if c == ']' and stack[-1] != '[':
                return False

            stack.pop()

        if len(stack) > 0:
            return False
        return True
