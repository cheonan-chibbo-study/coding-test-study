def solution(phone_book):
    p_set = set(phone_book)

    for p in phone_book:
        for i in range(1, len(p)):
            if p[0:i] in p_set:
                return False

    return True