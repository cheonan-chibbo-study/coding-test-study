# 문제링크

# 30분내 어디까지 풀었는가
30분넘어서 풀었다. 
그러나 정확성 테스트는 초과했지만 효율성 테스트는 전부 실패했다
```java
import java.util.*;

class Solution {

    public int[][] solution(int[][] rc, String[] operations) {
        for (String op : operations) {
            if (op.equals("Rotate")) {
                rc = rotate(rc);
            } else if (op.equals("ShiftRow")) {
                rc = shiftRow(rc);
            }
        }
        return rc;
    }

    // 모든 행을 아래로 한 칸씩 내리고, 마지막 행은 맨 위로 올림
    public int[][] shiftRow(int[][] rc) {
        int r = rc.length;
        int c = rc[0].length;

        int[][] newRc = new int[r][c];

        // 기존 0~r-2행을 아래로 한 칸씩 이동
        for (int i = 0; i < r - 1; i++) {
            for (int j = 0; j < c; j++) {
                newRc[i + 1][j] = rc[i][j];
            }
        }

        // 마지막 행을 맨 위로 이동
        for (int j = 0; j < c; j++) {
            newRc[0][j] = rc[r - 1][j];
        }

        return newRc;
    }

    // 바깥 테두리를 시계 방향으로 한 칸 회전
    public int[][] rotate(int[][] rc) {
        int r = rc.length;
        int c = rc[0].length;

        int[][] newRc = new int[r][c];

        // 일단 전체 복사
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                newRc[i][j] = rc[i][j];
            }
        }

        // 테두리가 1행 또는 1열인 경우는 따로 처리
        if (r == 1 && c == 1) return newRc;

        // 위쪽 가로줄: (0,0) ~ (0,c-2) -> 오른쪽으로 이동
        for (int j = 0; j < c - 1; j++) {
            newRc[0][j + 1] = rc[0][j];
        }

        // 오른쪽 세로줄: (0,c-1) ~ (r-2,c-1) -> 아래로 이동
        for (int i = 0; i < r - 1; i++) {
            newRc[i + 1][c - 1] = rc[i][c - 1];
        }

        // 아래쪽 가로줄: (r-1,1) ~ (r-1,c-1) -> 왼쪽으로 이동
        for (int j = 1; j < c; j++) {
            newRc[r - 1][j - 1] = rc[r - 1][j];
        }

        // 왼쪽 세로줄: (1,0) ~ (r-1,0) -> 위로 이동
        for (int i = 1; i < r; i++) {
            newRc[i - 1][0] = rc[i][0];
        }

        return newRc;
    }
}
```
# 왜 못풀었는가 - 
### 풀이가 안떠올랐는지 / 어떻게 풀지는 알겠는데 구현을 못하겠다.

# 접근방법
단순 배열로할시  효율성 테스트를 통과하지 못한다 그러므로 Deque를 써서 해결한다  
Deque를 써서 행렬을 맨 왼쪽 열, 맨 오른쪽 열, 가운데 값들(행으로 받음)어 로 쪼개서 관리한다  
1 2 3  
4 5 6  
7 8 9
left = [1, 4, 7]  
middle = [[2], [5], [8]]  
right = [3, 6, 9]  

ShiftRow 구현시
Deque의 pollLast 인 마지막 값을 먼저 꺼내는것을 사용해 행이 한칸씩 이동하는것울 구한다
Rotate 구현시
테두리만 움직이면 되니
몇 개 deque 에서 앞뒤 원소만을 옮긴다

# 배운점 
Deque를 이욜해 행렬 문제룰 풀수있다는것으 알게됬다