from heapq import heappush, heappop

def solution(jobs):
    jobs.sort()

    count = 0
    last_time = -1
    time = 0
    total_time = 0
    q = []

    while count < len(jobs):
        for job in jobs:
            request_time, duration = job
            if last_time < request_time <= time:
                heappush(q, [duration, request_time])

        if q:
            duration, request_time = heappop(q)
            last_time = time
            time += duration
            total_time += (time - request_time)
            count += 1
        else:
            time += 1

    return total_time // len(jobs)
