from collections import Counter

def solution(topping):
    a_topping = set()
    b_topping = Counter(topping);

    # 메인 로직
    answer = 0

    for t in topping:
        a_topping.add(t)
        b_topping[t] -= 1

        if b_topping[t] == 0:
            del b_topping[t]

        if len(a_topping) == len(b_topping):
            answer += 1

    return answer