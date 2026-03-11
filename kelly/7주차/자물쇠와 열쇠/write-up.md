## 👻 재시도 기록

### 재시도 1회

제한 시간 내 혼자서 문제를 풀지 못했다.

이번 재시도에서는 격자를 오른쪽으로 90도 회전시키는 코드를 잘 기억하면서 작성하기 쉽도록 하기 위해 아래 테크닉을 도입해 문제를 풀었다.

```python
matrix = [
						[1, 2, 3],
						[4, 5, 6],
						[7, 8, 9]
				 [

# matrix를 오른쪽으로 90도 회전
rotated = [list(r) for r in zip(*matrix[::-1])]
```

덕분에 이전보다 훨씬 코드 작성이 편했고 아래와 같은 코드를 작성해 제출했는데 최종 채점 케이스에서 20%의 케이스가 실패 처리 되었다.

```python
from copy import deepcopy

def solution(key, lock):
    # 메서드
    def match(row_offset, col_offset, rot):
        rotated_key = rotate(rot)
        for lock_row in range(N):
            for lock_col in range(N):
                key_row, key_col = lock_row + row_offset, lock_col + col_offset
                
                if key_row < 0 or key_row >= M or key_col < 0 or key_col >= M:
                    continue
                
                if lock[lock_row][lock_col] == 0 and rotated_key[key_row][key_col] == 0:
                    return False
                elif lock[lock_row][lock_col] == 1 and rotated_key[key_row][key_col] == 1:
                    return False
        
        return True
                
    def rotate(rot):
        rotated = deepcopy(key)
        for i in range(rot):
            rotated = [list(r) for r in zip(*rotated[::-1])]
        
        return rotated
    
    # 메인 로직
    M = len(key)
    N = len(lock)
    
    for row_offset in range(M - 1, -(N - 1), -1):
        for col_offset in range(M - 1, -(N - 1), -1):
            for rot in range(4):
                if match(row_offset, col_offset, rot):
                    return True
    
    return False
```

결국 혼자서 잘못된 부분을 찾지 못해 GPT와 이전 write-up을 참고해 코드를 수정했다.

### 통과하지 못한 이유

우선 내가 처음 작성한 코드가 틀린 이유는 다음과 같다.

- key_offset 루프 범위가 잘못되어있다.
    - 기존 코드는 `range(M - 1, -(N - 1), -1)` 이렇게 되어있는데
    - `range(M - 1, -N, -1)` 이렇게 수정해야한다…
- 키 위치가 범위 밖인 경우 자물쇠 부분이 0이면 False 처리해야하는데 이 처리 로직이 빠져있다.

### 코드 수정

위 잘못된 부분을 수정해 코드를 작성하면 다음과 같다.

```python
from copy import deepcopy

def solution(key, lock):
    # 메서드
    def match(key_row_offset, key_col_offset, rot):
        rotated_key = rotate(rot)
        for lock_row in range(N):
            for lock_col in range(N):
                lock_value = lock[lock_row][lock_col]
                key_row, key_col = lock_row + key_row_offset, lock_col + key_col_offset
                
                if 0 <= key_row < M and 0 <= key_col < M:
                    lock_value += rotated_key[key_row][key_col]
                
                if lock_value != 1:
                    return False
        
        return True
                
    def rotate(rot):
        rotated = deepcopy(key)
        for i in range(rot):
            rotated = [list(r) for r in zip(*rotated[::-1])]
        
        return rotated
    
    # 메인 로직
    M = len(key)
    N = len(lock)
    
    for key_row_offset in range(M - 1, -N, -1):
        for key_col_offset in range(M - 1, -N, -1):
            for rot in range(4):
                if match(key_row_offset, key_col_offset, rot):
                    return True
    
    return False
```

이 코드는 최종 정답 처리를 받는다.