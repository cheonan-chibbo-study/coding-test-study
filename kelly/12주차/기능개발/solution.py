from collections import deque

def solution(progresses, speeds):
    # 메인 로직
    dq = deque()
    for idx, item in enumerate(progresses):
        dq.append((item, idx))

    answer = []
    while dq:
        today_count = 1
        first_p, first_idx = dq.popleft()
        first_need_day = (100 - first_p) // speeds[first_idx]
        if first_need_day * speeds[first_idx] < 100 - first_p:
            first_need_day += 1

        while dq:
            cur_item, cur_idx = dq[0]
            if cur_item + (speeds[cur_idx] * first_need_day) >= 100:
                dq.popleft()
                today_count += 1
            else:
                break

        answer.append(today_count)

    return answer