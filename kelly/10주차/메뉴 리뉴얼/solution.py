from itertools import combinations
from collections import Counter

def solution(orders, course):
    # 전역 데이터
    new_orders = []
    for order in orders:
        new_orders.append(sorted(list(order)))

    # 메인 로직
    answer = []
    for size in course:
        candi_list = []

        for order in new_orders:
            combi_list = combinations(order, size)
            for candi in combi_list:
                candi_list.append(''.join(candi))

        if not candi_list:
            continue

        count = Counter(candi_list)
        max_count = max(count.values())
        if max_count < 2:
            continue

        for k, v in count.items():
            if v == max_count:
                answer.append(k)

    return sorted(answer)