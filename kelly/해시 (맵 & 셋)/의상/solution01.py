def solution(clothes):
    c_dict = {}

    for v1, v2 in clothes:
        c_dict[v2] = c_dict.get(v2, 0) + 1

    answer = 1
    for v in c_dict.values():
        answer *= (v + 1)

    return answer - 1