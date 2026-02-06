from collections import deque

def solution(queue1, queue2):
    # 메인 로직
    q1_sum = sum(queue1)
    q2_sum = sum(queue2)
    q1 = deque(queue1)
    q2 = deque(queue2)

    count = 0
    for i in range(4 * len(queue1)):
        if q1_sum == q2_sum:
            return count
        elif q1_sum > q2_sum:
            tmp = q1.popleft()
            q2.append(tmp)
            q1_sum -= tmp
            q2_sum += tmp
        else:
            tmp = q2.popleft()
            q1.append(tmp)
            q1_sum += tmp
            q2_sum -= tmp
        count += 1

    return -1