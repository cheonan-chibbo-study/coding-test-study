from itertools import combinations
from collections import Counter

def solution(orders, course):
    # 메인 로직
    answer = []
    new_orders = []
    for order in orders:
        new_orders.append(sorted(order))

    for size in course:
        candies = []
        for order in new_orders:
            # 1. 각 주문을 정렬한 뒤 해당 크기의 조합을 생성
            # 정렬을 미리 해야 "AB", "BA"가 같은 것으로 처리됨
            for combi in combinations(order, size):
                candies.append(''.join(combi))

        # 2. 해당 크기의 모든 조합의 빈도수 계산
        counter = Counter(candies)

        # 3. 가장 많이 주문된 횟수 확인 (최소 2번 이상)
        if len(counter) != 0 and max(counter.values()) >= 2:
            max_value = max(counter.values())
            for menu, count in counter.items():
                if count == max_value:
                    answer.append(menu)

    return sorted(answer)