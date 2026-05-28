from collections import defaultdict

def solution(id_list, report, k):
    # 메인 로직
    history = defaultdict(set)

    for r in report:
        req, target = r.split(" ")
        history[target].add(req)

    call_count = defaultdict(int)
    for v in history.values():
        if len(v) < k:
            continue

        for req in v:
            call_count[req] += 1

    answer = []
    for id in id_list:
        answer.append(call_count[id])

    return answer