from collections import defaultdict

def solution(today, terms, privacies):
    curr_year, curr_month, curr_day = tuple(map(int, today.split(".")))

    terms_map = defaultdict(int)
    for term in terms:
        name, period = term.split()
        terms_map[name] = int(period)

    answer = []
    for i, privacy in enumerate(privacies):
        start_date, name = privacy.split()
        end_year, end_month, end_day = tuple(map(int, start_date.split(".")))
        period = terms_map[name]

        # 1차로 약관의 기간을 월에 더한다.
        end_month += period
        # 1차 보정
        if end_month > 12:
            while end_month > 12:
                end_year += 1
                end_month -= 12

        # 하루를 뺀다.
        if end_day == 1:
            end_day = 28

            if end_month == 1:
                end_month = 12
                end_year -= 1
            else:
                end_month -= 1
        else:
            end_day -= 1

        if (end_year < curr_year) or (end_year == curr_year and end_month < curr_month) or (end_year == curr_year and end_month == curr_month and end_day < curr_day):
            answer.append(i + 1)

    return answer
