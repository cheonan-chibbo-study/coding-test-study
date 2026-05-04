def solution(phone_book):
    p_dict = {}

    for p in phone_book:
        target = ""
        for c in p:
            target += c
            p_dict[target] = p_dict.get(target, 0) + 1

    for p in phone_book:
        if p_dict[p] > 1:
            return False

    return True