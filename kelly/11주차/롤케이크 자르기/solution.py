from collections import Counter

def solution(topping):
    # 메인 로직
    set_a = set()
    dict_b = Counter(topping)
    answer = 0

    for t in topping:
        set_a.add(t)
        dict_b[t] -= 1
        if dict_b[t] == 0:
            del dict_b[t]

        if len(set_a) == len(dict_b):
            answer += 1

    return answer