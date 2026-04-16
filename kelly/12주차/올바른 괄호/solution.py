def solution(s):
    stack = []
    for ch in s:
        if ch == "(":
            stack.append(")")
            continue

        if not stack:
            return False

        stack.pop()

    return not stack