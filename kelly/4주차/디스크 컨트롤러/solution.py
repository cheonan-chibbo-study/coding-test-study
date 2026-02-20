from heapq import heapify, heappush, heappop
from collections import deque

def solution(jobs):
    # 전역 데이터
    jobs_count = len(jobs)
    new_jobs = []
    for id in range(jobs_count):
        new_jobs.append((jobs[id][1], jobs[id][0], id))
    new_jobs.sort(key = lambda x: (x[1], x[0], x[2]))
    new_jobs = deque(new_jobs)

    # 메인 로직
    total_times = [0] * jobs_count

    first_job = new_jobs.popleft()
    pq = [first_job]
    time = 0

    while pq:
        need_time, req_time, cur_id = heappop(pq)
        if time < req_time:
            time = req_time

        time += need_time
        total_times[cur_id] = time - req_time

        while new_jobs and new_jobs[0][1] <= time:
            heappush(pq, new_jobs.popleft())

        if new_jobs and not pq:
            heappush(pq, new_jobs.popleft())

    return sum(total_times) // jobs_count