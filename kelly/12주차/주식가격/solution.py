def solution(prices):
    stack = []
    answer = [-1] * len(prices)
    for idx, p in enumerate(prices):
        while stack and stack[-1][1] > p:
            popped = stack.pop()
            answer[popped[0]] = idx - popped[0]

        stack.append((idx, p))

    for item in stack:
        answer[item[0]] = len(prices) - item[0] - 1

    return answer