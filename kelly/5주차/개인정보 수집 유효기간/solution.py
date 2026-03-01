def solution(today, terms, privacies):
    # 메서드
    # ✅ 해시테이블에 약관별 유효기간을 일수로 계산해 저장한다.
    def calc_due(time):
        return ((time[0] - 2000) * 12 * 28) + ((time[1] - 1) * 28) + (time[2] - 1)

    # 메인 로직
    answer = []
    term_dict = {}
    for t in terms:
        name, term = t.split()
        term_dict[name] = int(term) * 28

    today = [int(i) for i in today.split(".")]
    today_due = calc_due(today)

    for idx, p in enumerate(privacies):
        start, name = p.split()
        start = [int(i) for i in start.split(".")]
        cur_due = calc_due(start)

        # ✅ 개인정보 수집일로부터 며칠이 지났는지 구한다.
        # ✅ 약관 유효기간보다 오래됐다면 인덱스를 정답 리스트에 추가한다.
        if term_dict[name] <= today_due - cur_due:
            answer.append(idx + 1)

    return answer