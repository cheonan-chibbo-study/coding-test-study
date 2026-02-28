from collections import defaultdict

def solution(id_list, report, k):
    n = len(id_list)
    idx_map = {x: i for i, x in enumerate(id_list)} # ID:index

    counter = defaultdict(int) # ID:신고 당한 횟수
    record = defaultdict(set) # 신고를 당한 ID:신고 한 ID
    for r in report:
        reporter, reported = r.split()

        if reporter in record[reported]: # 이미 동일한 신고 이력이 있다면 pass
            continue

        record[reported].add(reporter) # 신고 이력 저장
        counter[reported] += 1 # 신고 당한 횟수 추가

    answer = [0] * n
    for reported, count in counter.items():
        if count >= k:
            reporters = record[reported]
            for reporter in reporters:
                reporter_idx = idx_map[reporter]
                answer[reporter_idx] += 1
    return answer


