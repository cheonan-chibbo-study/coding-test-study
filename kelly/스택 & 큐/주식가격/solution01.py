def solution(prices):
    stack = []
    answer = [0] * len(prices)

    for day in range(len(prices)):
        if stack:
            while stack and stack[-1][0] > prices[day]:
                popped = stack.pop()
                answer[popped[1]] = day - popped[1]

        stack.append((prices[day], day))

    while stack:
        popped = stack.pop()
        answer[popped[1]] = (len(prices) - 1) - popped[1]

    return answer