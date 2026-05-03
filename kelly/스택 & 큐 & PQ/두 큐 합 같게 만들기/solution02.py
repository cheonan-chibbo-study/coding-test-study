def solution(queue1, queue2):
    q_size = len(queue1)
    q1_sum = sum(queue1)
    q2_sum = sum(queue2)

    dq = queue1[::]
    dq.extend(queue2)

    answer = 0
    left = 0
    right = q_size

    for i in range(q_size * 4):
        if q1_sum == q2_sum:
            return answer

        if q1_sum > q2_sum:
            q1_sum -= dq[left]
            q2_sum += dq[left]
            left = (left + 1) % len(dq)
        else:
            q1_sum += dq[right]
            q2_sum -= dq[right]
            right = (right + 1) % len(dq)

        answer += 1

    return -1