answer = 0

def solution(relation):
    recurse(0, [], relation)
    return answer

def recurse(i, selected, relation):
    global answer

    if i == len(relation[0]):
        if len(selected) == 0: return
        if is_uniqueness_satisfied(selected, relation) and is_minimality_satisfied(selected, relation):
            answer += 1
        return

    # i번 인덱스(속성)을 선택하는 경우
    selected.append(i)
    recurse(i + 1, selected, relation)
    selected.pop()

    # i번 인덱스(속성)을 선택하지 않는 경우
    recurse(i + 1, selected, relation)


# 유일성을 만족하는 지 확인한다.
# 릴레이션에 있는 모든 튜플에 대해서 유일하게 식별되어야 한다.
def is_uniqueness_satisfied(selected, relation):
    data = []
    for i in range(len(relation)):
        tuple = relation[i]
        row = []
        for attribute in selected:
            row.append(tuple[attribute])
        data.append(row)

    for i in range(len(data)):
        for j in range(len(data)):
            if i == j: continue
            if data[i] == data[j]:
                return False
    return True

# 최소성을 만족하는 지 확인한다.
# selected에서 하나씩 제외해서 유일성을 만족하는 지 판별한다. 만약 유일성이 만족되면 최소성은 불만족이다.
def is_minimality_satisfied(selected, relation):
    if len(selected) == 1:
        return is_uniqueness_satisfied(selected, relation)

    for except_index in selected:
        excepted = [x for x in selected if x != except_index]
        if is_uniqueness_satisfied(excepted, relation):
            return False
    return True
