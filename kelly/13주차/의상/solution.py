from itertools import combinations

def solution(clothes):
    # 메인 로직
    c_dict = {}
    for item, category in clothes:
        if category in c_dict:
            c_dict[category] += 1
        else:
            c_dict[category] = 1

    answer = 1
    for v in c_dict.values():
        answer *= (v + 1)

    return answer - 1