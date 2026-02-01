def solution(key, lock):
    # 전역 데이터
    M = len(key)
    N = len(lock)
    
    # 메서드
    def match(keyRowOffset, keyColOffset, rot):
        curKey = getCurKey(rot)
        for lockRow in range(N):
            keyRow = lockRow + keyRowOffset
            for lockCol in range(N):
                keyCol = lockCol + keyColOffset
                
                if not keyIsSafe(keyRow, keyCol):
                    if lock[lockRow][lockCol] == 0:
                        return False
                    continue
                
                if (lock[lockRow][lockCol] == 1 and curKey[keyRow][keyCol] == 1) or (lock[lockRow][lockCol] == 0 and curKey[keyRow][keyCol] != 1):
                    return False
        return True
    
    def getCurKey(rot):
        if rot == 0:
            return key
        
        curKey = [[0 for _ in range(M)] for _ in range(M)]
        for row in range(M):
            for col in range(M):
                if rot == 1:
                    curKey[col][M - row - 1] = key[row][col]
                elif rot == 2:
                    curKey[M - row - 1][M - col - 1] = key[row][col]
                else:
                    curKey[M - col - 1][row] = key[row][col]
                
        return curKey
    
    def keyIsSafe(row, col):
        return row >= 0 and row < M and col >= 0 and col < M
    
    # 메인 로직
    for keyRowOffset in range(M - 1, -N, -1):
        for keyColOffset in range(M - 1, -N, -1):
            for rot in range(4):
                if match(keyRowOffset, keyColOffset, rot):
                    return True
    
    return False