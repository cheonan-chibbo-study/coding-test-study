from heapq import heappush, heappop
from collections import deque

def solution(priorities, location):
    pq = []
    dq = deque()
    for i, v in enumerate(priorities):
        heappush(pq, -v)
        dq.append((v, i))

    answer = 1
    while dq:
        v, i = dq.popleft()

        if v == -pq[0]:
            if i == location:
                return answer

            heappop(pq)
            answer += 1
        else:
            dq.append((v, i))

    return -1