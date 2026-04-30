from itertools import combinations

def solution(relation):
    key_items = [i for i in range(0, len(relation[0]))]
    candi_key = []
    answer = 0

    # 메서드
    def is_candi_key(k):
        # 최소성 검사
        for rk in candi_key:
            if rk.issubset(k):
                return False

        # 유일성 검사
        check_set = set()
        for r in relation:
            s = ""
            for i in k:
                s += r[i]

            check_set.add(s)

        if len(check_set) != len(relation):
            return False

        return True

    # 메인 로직
    for count in range(1, len(relation[0]) + 1):
        candi_list = [set(combi) for combi in combinations(key_items, count)]

        for candi in candi_list:
            if is_candi_key(candi):
                candi_key.append(candi)
                answer += 1

    return answer