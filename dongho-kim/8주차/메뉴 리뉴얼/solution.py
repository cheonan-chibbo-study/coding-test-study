from itertools import combinations
from collections import defaultdict

def solution(orders, course):
    # 모든 course 가지수에 대해서 모든 orders를 돌면서 조합을 만든다.
    # 만들어진 조합은 딕셔너리에 카운팅한다.
    counter = defaultdict(int)
    for count in course:
        for order in orders:
            order_combinations = list(combinations(sorted(list(order)), count))
            for order_combination in order_combinations:
                counter[order_combination] += 1

    # print(f'counter:{counter}')

    # 카운터 딕셔너리 순회하면서, 문자 조합의 개수만큼의 최대 개수를 구한다.
    max_counter = defaultdict(int)
    for key, value in counter.items():
        count = len(key)
        max_counter[count] = max(max_counter[count], value)

    # print(f'max_counter:{max_counter}')
    # print()

    # 정답을 구한다.
    answer = []
    for target_cnt in course:
        max_cnt = max_counter[target_cnt]

        # print(f'target_cnt: {target_cnt} max_cnt: {max_cnt}')

        for order_combination, cnt in counter.items():
            if (len(order_combination) == target_cnt) and (cnt >= 2) and (cnt == max_cnt):
                # print(f'order_combination, cnt: {order_combination}, {cnt}')
                answer.append(''.join(list(order_combination)))

    return sorted(answer)
