def solution(phone_book):
    num_dict = {}
    for p in phone_book:
        target = ""
        for n in list(p):
            target += n
            if target in num_dict:
                num_dict[target] += 1
            else:
                num_dict[target] = 1

    for p in phone_book:
        if num_dict[p] >= 2:
            return False

    return True