# 소요시간 -> 요청 시각 -> 작업 번호

from collections import deque
from heapq import heapify, heappush, heappop

def solution(jobs):
    # 메인 로직
    dq = deque(sorted([(job[1], job[0], i) for i, job in enumerate(jobs)], key=lambda x: (x[1], x[0], x[2])))
    pq = []

    job_times = [0] * len(jobs)
    cur_time = 0

    while dq or pq:
        while dq and dq[0][1] <= cur_time:
            heappush(pq, dq.popleft())

        if not pq:
            popped = dq.popleft()
            heappush(pq, popped)
            cur_time = popped[1]

        cur_job = heappop(pq)
        cur_time += cur_job[0]
        job_times[cur_job[2]] = cur_time - cur_job[1]

    return sum(job_times) // len(jobs)