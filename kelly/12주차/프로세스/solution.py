from collections import deque
from heapq import heapify, heappush, heappop

def solution(priorities, location):
    # 메인 로직
    dq = deque()
    heap = []
    for idx, pri in enumerate(priorities):
        dq.append((idx, pri))
        heappush(heap, -pri)

    answer = 1
    while dq:
        idx, pri = dq.popleft()
        maximum = -heappop(heap)

        if pri == maximum:
            if idx == location:
                return answer

            answer += 1
            continue

        dq.append((idx, pri))
        heappush(heap, -maximum)

    return -1