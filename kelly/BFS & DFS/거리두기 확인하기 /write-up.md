## 👀 제한 시간 안에 어디까지 해냈는가?

`21분 40초`만에 문제를 푸는데 성공했다. 풀이법 자체는 쉽게 떠올렸지만 구현 코드가 복잡해 시간이 좀 걸렸다. 어찌어찌 코드를 작성하긴 했지만 중복 코드가 매우 많은 썩 좋지 않은 코드가 나왔다. 작성한 코드는 다음과 같다.

```python
# 21분 40초만에 최종 정답 성공

def solution(places):
    # 전역 데이터
    dir = [[-1, 0], [0, 1], [0, -1], [1, 0]]
    
    # 메서드
    def check_p(p):
        for r in range(5):
            for c in range(5):
                if p[r][c] != "P":
                    continue
                if not search(p, r, c):
                    return 0
        
        return 1
    
    def search(p, r, c):
        for d in range(len(dir)):
            dr, dc = dir[d]
            next_r = r + dr
            next_c = c + dc
            
            if not is_safe(next_r, next_c) or p[next_r][next_c] == "X":
                continue
            if p[next_r][next_c] == "P":
                return False
            if not search_2(p, next_r, next_c, 3 - d):
                return False
        
        return True
    
    def search_2(p, r, c, ignore):
        for d in range(len(dir)):
            if d == ignore:
                continue
            
            dr, dc = dir[d]
            next_r = r + dr
            next_c = c + dc
            
            if not is_safe(next_r, next_c) or p[next_r][next_c] == "X" or p[next_r][next_c] == "O":
                continue
            if p[next_r][next_c] == "P":
                return False
            
        return True
    
    def is_safe(r, c):
        return r >= 0 and r < 5 and c >= 0 and c < 5
    
    # 메인 로직
    answer = []
    for p in places:
        answer.append(check_p(p))
    
    return answer
    
```

P & J 트레이닝

- Java로 1차 풀이를 진행하여 `29분 54초`만에 혼자서 문제를 해결했다.
- Python으로 2차 풀이를 진행하여 `18분 05초`만에 문제를 해결했...지만...
    - 최종 채점에서 몇몇 케이스가 오답 처리를 받았는데 원인 파악을 혼자 하지 못해서 잼미니의 도움을 받았다.
    - dq에 다음 경로를 추가하는 로직을 `if n_obj == “P”` 분기 안에 넣은게 문제였다…

---

## 🧑‍🔬 문제 분석

사람, 빈공간, 파티션으로 구성된 5 x 5 크기의 방정보를 5개 입력했을 때 각 방마다 사용자들이 모두 거리두기를 지키고 있는지 여부를 배열로 반환하는 문제이다.

- 거리두기 규칙은 문제를 참고하자.

문제에 주어진 제약 조건은 다음과 같다.

### 제한사항

- `places`의 행 길이(대기실 개수) = 5
    - `places`의 각 행은 하나의 대기실 구조를 나타냅니다.
- `places`의 열 길이(대기실 세로 길이) = 5
- `places`의 원소는 `P`,`O`,`X`로 이루어진 문자열입니다.
    - `places` 원소의 길이(대기실 가로 길이) = 5
    - `P`는 응시자가 앉아있는 자리를 의미합니다.
    - `O`는 빈 테이블을 의미합니다.
    - `X`는 파티션을 의미합니다.
- 입력으로 주어지는 5개 대기실의 크기는 모두 5x5 입니다.
- return 값 형식
    - 1차원 정수 배열에 5개의 원소를 담아서 return 합니다.
    - `places`에 담겨 있는 5개 대기실의 순서대로, 거리두기 준수 여부를 차례대로 배열에 담습니다.
    - 각 대기실 별로 모든 응시자가 거리두기를 지키고 있으면 1을, 한 명이라도 지키지 않고 있으면 0을 담습니다.

---

## 🤔 풀이 고민

### 완전 탐색

문제를 보자마자 떠올린 방식은 완전 탐색 방식이었다. 우선 문제에 주어진 배열 크기가 5 x 5로 아주 작은 값이기 때문에 충분히 완탐 로직으로 작성할 수 있다는 생각이 들었고, 사람의 위치를 기준으로 4방향, 그리고 이 중 빈공간이 있다면 그 공간을 기준으로 들어왔던 방향을 제외한 3방향을 모두 탐색해 조건을 비교하면 풀 수 있다.

### BFS

BFS 방식으로도 풀 수 있다. 사실 내가 위에 기술한 완전 탐색 방식이 큐를 사용하지 않았을 뿐 BFS 코드와 크게 다르지 않을거 같다.

### 결론

- 완전 탐색, BFS 방식으로 풀 수 있다.

---

## 🏃 코드 작성 과정

### 최종 정답 코드 개선

처음 작성한 코드는 최종 정답 처리는 받을 수 있지만 중복 코드도 많고 코드도 길어서 구현이 너무 오래걸린다. BFS 방식을 적용해 코드를 다음과 같이 개선했다.

```python
from collections import deque

def solution(places):
    # 전역 데이터
    dir = [[-1, 0], [0, 1], [0, -1], [1, 0]]
    
    # 메서드
    def has_distance(p):
        for r in range(5):
            for c in range(5):
                if p[r][c] != "P":
                    continue
                
                if not check_distance(p, r, c):
                    return False
                
        return True
    
    def check_distance(p, r, c):
        visited = [[False for _ in range(5)] for _ in range(5)]
        dq = deque([[r, c, 0]])
        visited[r][c] = True
        
        while dq:
            cur_r, cur_c, cur_d = dq.popleft()
            for dr, dc in dir:
                next_r = cur_r + dr
                next_c = cur_c + dc
                next_d = cur_d + 1
                
                if not is_safe(next_r, next_c) or visited[next_r][next_c] or p[next_r][next_c] == "X" or next_d > 2:
                    continue
                
                if p[next_r][next_c] == "P":
                    return False
                
                dq.append([next_r, next_c, next_d])
                visited[next_r][next_c] = True
        
        return True
    
    def is_safe(r, c):
        return r >= 0 and r < 5 and c >= 0 and c < 5
    
    # 메인 로직
    answer = [1] * 5
    for i in range(5):
        if not has_distance(places[i]):
            answer[i] = 0
        
    return answer
```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    from collections import deque
    
    def solution(places):
        # 메서드
        def check(place):
            visited = [[False] * len(place[0]) for _ in range(len(place))]
            for row in range(len(place)):
                for col in range(len(place[row])):
                    if place[row][col] == "P" and not visited[row][col]:
                        if not bfs(place, visited, row, col):
                            return False
            
            return True
        
        def bfs(place, visited, s_row, s_col):
            dq = deque([(s_row, s_col, "P")])
            visited[s_row][s_col] = True
            
            while dq:
                c_row, c_col, c_obj = dq.popleft()
                exist_person = False
                
                for dr, dc in [(-1, 0), (0, 1), (0, -1), (1, 0)]:
                    n_row, n_col = c_row + dr, c_col + dc
                    
                    if not is_safe(place, visited, n_row, n_col):
                        continue
                    
                    n_obj = place[n_row][n_col]
                    
                    if n_obj == "P":
                        if c_obj == "P" or exist_person:
                            return False
                        
                        exist_person = True
                        
                    if not visited[n_row][n_col]:
                        dq.append((n_row, n_col, n_obj))
                        visited[n_row][n_col] = True
            
            return True
        
        def is_safe(place, visited, row, col):
            return 0 <= row < len(place) and 0 <= col < len(place[row]) and place[row][col] != "X"
        
        # 메인 로직
        answer = []
        for i in range(len(places)):
            if check(places[i]):
                answer.append(1)
            else:
                answer.append(0)
        
        return answer
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        
        int[][] dir = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
        
        public int[] solution(String[][] places) {
            int[] answer = new int[places.length];
            
            for (int i = 0; i < places.length; i++) {
                if (check(places[i])) {
                    answer[i] = 1; 
                } else {
                    answer[i] = 0;
                }
            }
            
            return answer;
        }
        
        private boolean check(String[] place) {
            boolean[][] visited = new boolean[place.length][place[0].length()];
            for (int row = 0; row < place.length; row++) {
                for (int col = 0; col < place[row].length(); col++) {
                    if (place[row].charAt(col) == 'P' && !visited[row][col]) {
                        if (!bfs(place, visited, row, col)) {
                            return false;
                        }
                    }
                }
            }
            
            return true;
        }
        
        private boolean bfs(String[] place, boolean[][] visited, int sRow, int sCol) {
            Deque<int[]> dq = new ArrayDeque<>();
            dq.offer(new int[]{sRow, sCol});
            visited[sRow][sCol] = true;
            
            while (!dq.isEmpty()) {
                int[] cur = dq.poll();
                char curObj = place[cur[0]].charAt(cur[1]);
                boolean existPerson = false;
                
                for (int[] d : dir) {
                    int nRow = cur[0] + d[0];
                    int nCol = cur[1] + d[1];
                    
                    if (!isSafe(place, visited, nRow, nCol)) {
                        continue;
                    }
                    
                    char nextObj = place[nRow].charAt(nCol);
                    
                    if (nextObj == 'P') {
                        if (curObj == 'P' || existPerson) {
                            return false;
                        }
                        
                        existPerson = true;
                    }
                    
                    if (!visited[nRow][nCol]) {
                        dq.offer(new int[]{nRow, nCol});
                        visited[nRow][nCol] = true;
                    }
                }
            }
            
            return true;
        }
        
        private boolean isSafe(String[] place, boolean[][] visited, int row, int col) {
            return row >= 0 && row < place.length && col >= 0 && col < place[row].length()
                && place[row].charAt(col) != 'X';
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 코드를 더 간단하게 구현하는 연습을 많이 해야겠다.
- 다 풀어놓고 어처구니 없는 실수를 했다… 앞으로 조심하자.