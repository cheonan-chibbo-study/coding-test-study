from collections import deque

def solution(queue1, queue2):
    q1 = deque(queue1)
    q2 = deque(queue2)

    s1 = sum(q1)
    s2 = sum(q2)
    total_sum = s1 + s2

    # 전체 합이 홀수면 답을 구할 수 없다.
    if total_sum & 1 != 0:
        return -1

    answer = 0
    limit = len(q1) * 3 # 최대 반복 횟수 설정
    while answer <= limit:
        if s1 == s2:
            return answer

        if s1 > s2:
            x = q1.popleft()
            s1 -= x
            q2.append(x)
            s2 += x
            answer += 1
            continue

        if s1 < s2:
            x = q2.popleft()
            s2 -= x
            q1.append(x)
            s1 += x
            answer += 1

    return -1
