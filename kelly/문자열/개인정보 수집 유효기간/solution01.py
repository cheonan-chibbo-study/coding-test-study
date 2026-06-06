def solution(today, terms, privacies):
    t_map = {}
    for term in terms:
        k, v = term.split()
        t_map[k] = int(v) * 28

    # 메서드
    def count_end_day(p):
        date, term = p.split()
        return convert_day(date) + t_map[term]

    def convert_day(date):
        y, m, d = date.split(".")
        return (int(y) * 12 * 28) + (int(m) * 28) + int(d)

    # 메인 로직
    today_day = convert_day(today)
    answer = []

    for i, p in enumerate(privacies):
        end_day = count_end_day(p)

        if today_day >= end_day:
            answer.append(i + 1)

    return answer