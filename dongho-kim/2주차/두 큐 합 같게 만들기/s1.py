from collections import deque

def solution(queue1, queue2):
    q1 = deque(queue1)
    q2 = deque(queue2)

    answer = 0
    while True:
        if len(q1) == 0 or len(q2) == 0:
            return -1

        sum_q1 = sum(q1)
        sum_q2 = sum(q2)

        if sum_q1 == sum_q2:
            break

        if sum_q1 > sum_q2:
            x = q1.popleft()
            q2.append(x)
            answer += 1
            continue

        if sum_q1 < sum_q2:
            x = q2.popleft()
            q1.append(x)
            answer += 1

    return answer
