from itertools import combinations
from collections import Counter

def solution(orders, course):
    menus = [sorted(order) for order in orders]

    # 메서드
    def get_maximum_order(cnt):
        courses = []
        for menu in menus:
            combi = ["".join(c) for c in combinations(menu, cnt)]
            courses.extend(combi)

        order_count = Counter(courses)
        result = []
        maximum = -1
        for k, v in order_count.items():
            if v >= 2:
                if v > maximum:
                    result = [k]
                    maximum = v
                elif v == maximum:
                    result.append(k)

        return result

    # 메인 로직
    answer = []
    for cnt in course:
        maximum_order = get_maximum_order(cnt)
        answer.extend(maximum_order)

    return sorted(answer)