from collections import deque
from math import ceil

def solution(progresses, speeds):
    dq = deque([i for i in range(len(progresses))]);
    answer = []

    while dq:
        popped = dq.popleft()
        count = 1
        need_day = 0
        if (progresses[popped] < 100):
            need_day = ceil((100 - progresses[popped]) / speeds[popped])

        for i in range(popped + 1, len(progresses)):
            progresses[i] += need_day * speeds[i]

        while dq and progresses[dq[0]] >= 100:
            dq.popleft()
            count += 1

        answer.append(count)

    return answer