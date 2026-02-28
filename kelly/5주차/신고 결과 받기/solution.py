def solution(id_list, report, k):
    report_set = set(report)
    answer = [0] * len(id_list)

    record = {x: 0 for x in id_list}
    for r in report_set:
        record[r.split()[1]] += 1

    for r in report_set:
        req, target = r.split()
        if record[target] >= k:
            answer[id_list.index(req)] += 1

    return answer