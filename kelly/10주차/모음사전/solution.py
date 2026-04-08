from itertools import product

def solution(word):
    words = []
    for i in range(1, 6):
        # 'AEIOU'에서 중복을 허용해 i개를 뽑음
        for p in product("AEIOU", repeat=i):
            words.append(''.join(p))

    words.sort()
    return words.index(word) + 1