from collections import deque

def solution(queue1, queue2):
    dq1 = deque(queue1)
    dq2 = deque(queue2)

    q1_sum = sum(queue1)
    q2_sum = sum(queue2)

    answer = 0

    for i in range(4 * len(queue1)):
        if q1_sum == q2_sum:
            return answer

        if q1_sum > q2_sum:
            popped = dq1.popleft()
            dq2.append(popped)

            q1_sum -= popped
            q2_sum += popped
        else:
            popped = dq2.popleft()
            dq1.append(popped)

            q1_sum += popped
            q2_sum -= popped

        answer += 1

    return -1