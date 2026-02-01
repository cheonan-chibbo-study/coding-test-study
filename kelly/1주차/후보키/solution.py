def solution(relation):
    # 전역 데이터
    colCount = len(relation[0])
    colIndex = [i for i in range(colCount)]
    candiKeys = []

    # 메서드
    def getCandiCombi(keyCount, result, temp, start):
        if len(temp) == keyCount:
            result.append(set(temp))
            return

        for i in range(start, colCount):
            temp.append(colIndex[i])
            getCandiCombi(keyCount, result, temp, i + 1)
            temp.pop()
    
    def isMinimum(combi):
        for candi in candiKeys:
            if candi.issubset(combi):
                return False
        
        return True

    def isUnique(combi):
        checkSet = set()
        for r in relation:
            checkSet.add(tuple(r[c] for c in combi))
        
        return len(checkSet) == len(relation)

    # 메인 로직
    for keyCount in range(1, colCount + 1):
        # 키 조합 구하기
        candiCombi = []
        getCandiCombi(keyCount, candiCombi, [], 0)

        for combi in candiCombi:
            # 최소성 & 유일성 검사
            if not isMinimum(combi) or not isUnique(combi):
                continue

            candiKeys.append(combi)
    
    return len(candiKeys)