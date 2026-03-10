## 🥊 재시도

### 1회차

재시도 1회차에서는 격자를 오른쪽으로 이동시키는 코드를 파이선 `zip()`을 활용해서 풀었습니다.

zip() 활용법은 다음과 같습니다.

```python
matrix = [
						[1, 2, 3],
						[4, 5, 6],
						[7, 8, 9]
				 [

# matrix를 오른쪽으로 90도 회전
rotated = [list(r) for r in zip(*matrix[::-1])]
```

위 테크닉을 활용해 작성된 최종 코드는 다음과 같습니다.

```python
def solution(key, lock):
    # 전역 데이터
    M = len(key)
    N = len(lock)
    
    # 회전 함수 (90도 시계 방향)
    def rotate(matrix):
        return [list(row) for row in zip(*matrix[::-1])]
    
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
        curKey = key
        for _ in range(rot):
            curKey = rotate(curKey)
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
```