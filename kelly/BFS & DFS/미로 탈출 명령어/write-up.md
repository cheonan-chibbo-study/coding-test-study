## 👀 제한 시간 안에 어디까지 해냈는가?

제한 시간 `30분안에` 테스트 케이스를 통과하는 풀이 코드를 작성해 제출했지만 대부분의 최종 채점 케이스에서 시간 초과 판정을 받았다. 결국 시간 초과 문제를 해결하지 못했다.

마지막으로 작성한 코드는 다음과 같다.

```python
# 15분 38초 남은, 정렬 뭐지
# 14분 22초 시간 초과 발생

from collections import deque

def solution(n, m, x, y, r, c, k):
    # 전역 데이터
    DIR = [[-1, 0, "u"], [0, 1, "r"], [0, -1, "l"], [1, 0, "d"]]
    x, y, r, c = x - 1, y - 1, r - 1, c - 1
    
    # 메서드
    def is_safe(t_r, t_c):
        return t_r >= 0 and t_r < n and t_c >= 0 and t_c < m
    
    # 메인 로직
    candi = []
    dq = deque([(x, y, "")])
    
    while dq:
        cur_r, cur_c, his = dq.popleft()
        
        if len(his) == k:
            if (cur_r, cur_c) == (r, c):
                candi.append(his)
            continue
        
        for dr, dc, dm in DIR:
            next_r, next_c = cur_r + dr, cur_c + dc
            
            if not is_safe(next_r, next_c):
                continue
            
            next_his = his + dm
            dq.append((next_r, next_c, next_his))
    
    if not candi:
        return "impossible"
    
    return sorted(candi)[0]
```

P & J 트레이닝

- 스터디에서 파이썬으로 아래와 같이 코드를 작성해 테스트 케이스를 모두 통과 했지만 최종 채점에서 대부분의 케이스에 오답을 받았다.
    - 🤖 작성한 코드와 이 코드가 오답인 이유
        - 작성한 코드

            ```python
            from collections import deque
            
            def solution(n, m, x, y, r, c, k):
                dir = ((1, 0, 'd'), (0, -1, 'l'), (0, 1, 'r'), (-1, 0, 'u'))
                board = [[0] * (m + 2) for _ in range(n + 2)]
                for row in range(1, n + 1):
                    for col in range(1, m + 1):
                        board[row][col] = 1
                
                # 메인 로직
                dq = deque([(x, y, [])])
                visited = [[False] * len(board[0]) for _ in range(len(board))]
                visited[x][y] = True
                
                while dq:
                    cur_r, cur_c, step = dq.popleft()
                    
                    if cur_r == r and cur_c == c:
                        need_step = k - len(step)
                        
                        if need_step % 2 != 0:
                            return "impossible"
                        
                        # 남은 발걸음을 최소로 할 수 있도록 처리
                        next_dir = 0    
                        for d in range(len(dir)):
                            dr, dc, ds = dir[d]
                            next_r, next_c = cur_r + dr, cur_c + dc
                                
                            if board[next_r][next_c] == 1:
                                next_dir = d
                                break
                        
                        for s in range(0, need_step // 2):
                            step.append(dir[next_dir][2])
                        
                        for s in range(0, need_step // 2):
                            step.append(dir[abs(3 - next_dir)][2])
                        
                        return "".join(step)
            
                    for dr, dc, ds in dir:
                        next_r, next_c = cur_r + dr, cur_c + dc
                        
                        if board[next_r][next_c] != 1 or visited[next_r][next_c]:
                            continue
                        
                        next_step = step[::]
                        next_step.append(ds)
                        
                        dq.append((next_r, next_c, next_step))
                        visited[next_r][next_c] = True
                
                return "impossible"
            ```


        기존 코드가 가지고 있는 논리적인 한계와 왜 이 방식으로 풀었을 때 오답이 날 수밖에 없는지 **핵심 원인 3가지**를 아주 자세하게 짚어드릴게요.
        
        이 문제는 단순한 최단 경로 찾기가 아니라 "정확히 $k$번 움직여야 한다"와 "그중 사전 순으로 가장 빠른 문자열이어야 한다"는 까다로운 조건이 붙어 있기 때문에 일반적인 BFS 구조로는 해결하기 어렵습니다.
        
        ## 1. '사전 순 가장 빠른 경로'를 보장하지 못함 (조기 종료의 오류)
        
        현재 코드의 가장 큰 논리적 오류는 "목적지 `(r, c)`에 처음 도달했을 때 탐색을 즉시 종료하고 남은 발걸음을 패딩(`dlru`)하는 것"입니다.
        
        BFS는 가장 적은 이동 횟수(최단 거리)로 목적지에 도달하는 경로를 먼저 찾습니다. 하지만 이 문제에서 원하는 건 **최단 경로가 아니라, 정확히 $k$번 움직인 경로 중 사전 순으로 가장 빠른 경로**입니다.
        
        ### 💡 예시로 보기
        
        - 출발지: `(1, 1)`, 목적지: `(2, 2)`, $k = 4$
        - 이동 가능한 방향 사전 순: `d` (아래) -> `l` (왼쪽) -> `r` (오른쪽) -> `u` (위)
        1. **BFS가 처음으로 목적지를 찾는 경로 (현재 코드의 방식):**
            - `(1,1)`에서 `d`로 내려가고 `r`로 이동해서 `(2,2)`에 도달합니다. (경로: `dr`, 이동 횟수: 2)
            - 목적지에 도달했으니 남은 2걸음(`need_step = 2`)을 제자리에서 왔다 갔다 채웁니다. 만약 주변에 아래 공간(`d`)이 있다면 `d`와 `u`를 추가합니다.
            - 결과물: **`drdu`**
        2. **실제 사전 순으로 가장 빠른 정답 경로:**
            - 처음부터 사전 순으로 가장 빠른 `d` 방향으로 끝까지 내려갔다가 돌아오는 경로를 탐색해 봅니다.
            - `(1,1)` $\rightarrow$ `d` $\rightarrow$ `d` $\rightarrow$ `r` $\rightarrow$ `u` 순으로 움직여도 똑같이 4번 만에 `(2,2)`에 도착합니다.
            - 결과물: **`ddru`**
        
        > **비교:** `ddru`가 `drdu`보다 사전 순으로 훨씬 빠릅니다!
        > 
        > 
        > 즉, 목적지에 최단 거리로 도착한 뒤 제자리걸음을 하는 것보다, **애초에 다른 곳을 우회해서(사전 순이 빠른 방향으로) 목적지에 딱 맞춰 들어오는 것이 훨씬 유리**합니다. 현재 코드는 처음 목적지를 만나면 바로 멈추기 때문에 이 우회 경로를 절대 찾을 수 없습니다.
        > 
        
        ## 2. 2차원 `visited` 배열 사용으로 인한 경로 차단
        
        현재 코드에서는 한 번 방문한 칸을 다시 방문하지 못하도록 `visited[next_r][next_c] = True` 처리를 하고 있습니다.
        
        이 문제의 조건 중에는 "같은 미로의 칸을 여러 번 방문해도 됩니다"라는 조건이 명시되어 있습니다. 정확히 $k$번을 채우기 위해서는 이전에 지나왔던 칸을 다시 돌아가거나 맴도는 행위가 필수적입니다.
        
        하지만 `visited` 배열로 방문을 막아버리면 다음과 같은 문제가 생깁니다.
        
        - 다른 최적의 경로(사전 순으로 더 빠른 경로)가 뒤늦게 해당 칸을 지나가려고 할 때, 이미 먼저 도착한 엉뚱한 경로 때문에 **방문이 차단**되어 탐색이 불과해집니다.
        - 그렇다고 `visited` 배열을 지워버리면, 무한 루프에 빠져 큐에 데이터가 무한히 쌓이게 됩니다.
        
        ## 3. 메모리 및 시간 초과 (BFS의 한계)
        
        문제에서 $k$의 최댓값은 **2,500**입니다.
        
        일반적인 BFS는 매 단계마다 4가지 방향으로 가지치기가 일어납니다. $k$번 이동할 때까지 큐에 쌓이는 상태의 수는 이론적으로 최대 $4^{2500}$에 달할 수 있습니다.
        
        비록 격자 범위 제한과 `visited`로 어느 정도 막는다고 해도, $k$가 수백~수천 단위가 되면 큐에 들어가는 `(x, y, step_list)` 데이터가 기하급수적으로 늘어나 **메모리 초과(Memory Limit Exceeded)** 혹은 **시간 초과**를 피할 수 없습니다. 게다가 파이썬에서 `next_step = step[::]` 처럼 매번 리스트를 복사(Deep Copy)하는 연산은 메모리와 시간을 엄청나게 잡아먹는 주범입니다.
        
        ## 🛠️ 올바른 접근 방향은?
        
        이 문제를 통과하려면 BFS 대신 **그리디(Greedy, 탐욕법)** 알고리즘을 사용해야 합니다.
        
        1. **사전 순 우선 탐색:** 매 걸음마다 굳이 큐에 다 넣지 말고, 무조건 `d` $\rightarrow$ `l` $\rightarrow$ `r` $\rightarrow$ `u` 순서로 딱 한 걸음만 가봅니다.
        2. **수학적 가지치기:** 그 한 걸음을 걸었을 때, "남은 이동 횟수로 목적지까지 도달하는 게 가능한가?"를 맨해튼 거리($|x_1 - x_2| + |y_1 - y_2|$)로 계산합니다.
        3. 도달 가능하다면 그 방향을 **확정** 짓고 다음 걸음으로 넘어갑니다. (사전 순으로 가장 빠른 걸 선택했으니 뒤는 돌아볼 필요도 없습니다.)
        
        이렇게 풀면 큐를 쓸 필요 없이 정확히 $k$번 반복하는 `for`문 하나만으로 문제를 해결할 수 있게 됩니다.

- 결국 기존에 작성한 write-up을 참고해서 문제를 풀었다. 이 문제는 꼭 복습할 필요가 있다.

---

## 🧑‍🔬 문제 분석

문제에 주어진 조건들을 충족해 미로를 탈출할 수 있는 경로 혹은 탈출할 수 없다는 결과를 반환하는 문제이다.

- 자세한 내용은 문제를 살펴보자.

문제에 주어진 제약 조건은 다음과 같다.

### 제한사항

- 2 ≤ `n` (= 미로의 세로 길이) ≤ 50
- 2 ≤ `m` (= 미로의 가로 길이) ≤ 50
- 1 ≤ `x` ≤ `n`
- 1 ≤ `y` ≤ `m`
- 1 ≤ `r` ≤ `n`
- 1 ≤ `c` ≤ `m`
- (`x`, `y`) ≠ (`r`, `c`)
- 1 ≤ `k` ≤ 2,500

---

## 🤔 풀이 고민

처음 `k` 를 보자마자 BFS를 떠올렸고 대충 격자 크기가 최대 50 x 50으로 작으니까 BFS를 visited 없이 무지성 수행해도 문제를 해결할 수 있을거 생각했다. 하지만 이런 방식을 사용하면 시간 초과가 발생한다.

- visited가 없기 때문에 이동할 수 있는 4가지 경로를 시작으로 최대 k 크기인 2,500번의 이동이 발생할 수 있어 이 때 예상되는 시간 복잡도는 `O(4^2,500)`이다… 이미 여기서부터 이 방식으로는 절대 풀지 못하는 결론이 나온다.
- 내가 처음 작성한 코드는 k번으로 목적지에 도착할 수 있는 모든 문자열 경로를 수집 후 마지막에 문자열 리스트를 사전 정렬해서 첫 번째 요소를 반환하고 있다. 당장 이것만 해도 굉장히 많은 후보군이 모일경우 정렬에 큰 비용이 들 수 있다. (물론 첫번째 발생하는 4^2,500 연산으로 먼저 터진다…)

결론적으로 처음 내가 생각한 방식은 너무 많은 경우의 수를 탐색하기 때문에 BFS 탐색 횟수를 크게 줄일 아이디어가 필요했다. 이 아이디어를 혼자서 찾지는 못해 결국 구글링을 했고 다음과 같은 아이디어를 얻을 수 있었다.

### Manhattan 공식으로 목적지 까지 최단 거리를 구한 후 도달 가능한 케이스만 구성하기

`Manhattan` 거리 공식을 활용하면 시작 위치에서 도착 위치 까지의 최단 거리를 구할 수 있다. 만약 (x1, y1) → (x2, y2)의 최단 거리를 구하고 싶다면 다음과 같다.

- `abs(x2 - x1) + abs(y2 - y1)`

manhattan 공식으로 최단 거리를 확보했다면 이제 이 값을 활용해 경우의 수를 줄여야 한다. 아이디어는 다음과 같다.

- 각 케이스에서 다음 이동 경로를 바로 큐에 넣지 말고, 다음 이동하는 포인트에서 최단 거리 만에 이동할 수 있는 거리가 남았는지 검사한다.
- 만약 현재 남은 이동 횟수로 목적지에 도달하는 최단 거리를 갈 수 있을 경우, (현재 남은 횟수 - 최단거리) 값이 짝수인지 검사한다.
    - 만약 내가 이동거리가 4 남았는데 최단 거리까지 3이라고 가정할 경우, 우선 3으로 목적지에 도착후 남은 횟수를 제거해야하는데 문제는 남은 횟수가 홀수이면 다시 목적지로 돌아올 수 없다. 따라서 이 경우는 목적지에 도달할 수 없는 경우로 간주한다.

이 아이디어를 활용하면 애초에 목적지에 도착할 수 없는 경우는 큐에 넣지 않기 때문에 탐색 횟수를 줄일 수 있다.

### DIR 배열 요소 순서를 사전순으로 설정 & 4방향 중 목적지에 도달할 수 있는 경우가 발생하면 조기 종료

내가 처음 작성한 코드의 문제점 중 하나는 이동 가능한 모든 문자 경로를 모아 정렬하는 비효율적인 로직의 존재이다. 이 비효율 역시 위 소제목 방식으로 없엘 수 있다.

애초에 이동 방향 중 사전순으로 가장 빠른 방향부터 검사해서 이동 가능한 방향이 나오면 남은 방향은 굳이 탐색할 필요가 없다. 애초에 남은 방향은 사전순으로 밀리기 때문에 탐색해도 그 케이스는 전부 후보에 들지 못한다.

### 정리

- 내 기존 코드는 visited가 없어 최대 4^2,500번 탐색 + 사전순으로 후보에 들 수 없는 경우까지 모두 탐색하는 비효울 로직으로 시간 초과가 발생한다.
- 따라서 위에 기술한 아이디어들을 적용해서 탐색 횟수를 대폭 낮춰야한다.
- 아이디어는 아래 블로그를 참고했다.

  [[프로그래머스 Lv3] 파이썬 - 미로 탈출 명령어](https://cheon2308.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-Lv3-%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EB%AF%B8%EB%A1%9C-%ED%83%88%EC%B6%9C-%EB%AA%85%EB%A0%B9%EC%96%B4)


---

## 🏃 코드 작성 과정

### 최종 정답 코드 작성

기존에 내가 작성한 코드에서 위 아이디어를 적용해 다음과 같이 개선했다. 이 코드는 최종 정답 처리를 받는다.

```python
from collections import deque

def solution(n, m, x, y, r, c, k):
    # 전역 데이터
    DIR = [[1, 0, "d"], [0, -1, "l"], [0, 1, "r"], [-1, 0, "u"]]
    x, y, r, c = x - 1, y - 1, r - 1, c - 1
    
    # 메서드
    def is_safe(t_r, t_c):
        return t_r >= 0 and t_r < n and t_c >= 0 and t_c < m
    
    def manhattan(row, col):
        return abs(r - row) + abs(c - col)
    
    # 메인 로직
    if manhattan(x, y) > k or (k - manhattan(x, y)) % 2:
        return "impossible"
    
    dq = deque([(x, y, "")])
    while dq:
        cur_r, cur_c, his = dq.popleft()

        if (cur_r, cur_c) == (r, c):
            if len(his) == k:
                return his
            elif (k - len(his) - manhattan(cur_r, cur_c)) % 2:
                return "impossible"
        
        for dr, dc, dm in DIR:
            next_r, next_c = cur_r + dr, cur_c + dc
            
            if not is_safe(next_r, next_c) or manhattan(next_r, next_c) + len(his) + 1 > k:
                continue

            dq.append((next_r, next_c, his + dm))
            break
    
    return "impossible"

```

---

## 🧑‍💻 최종 정답 코드

### Python 풀이

- solution01

    ```python
    from collections import deque
    
    def solution(n, m, x, y, r, c, k):
        dir = ((1, 0, "d"), (0, -1, "l"), (0, 1, "r"), (-1, 0, "u"))
        
        board = [[0] * (m + 2) for _ in range((n + 2))]
        for row in range(1, n + 1):
            for col in range(1, m + 1):
                board[row][col] = 1
                
        # 메서드
        def manhattan(row, col):
            return abs(r - row) + abs(c - col)
        
        def is_safe(row, col, step):
            if manhattan(row, col) > k - len(step) - 1:
                return False
            
            if board[row][col] != 1:
                return False
            
            return True
        
        # 메인 로직
        if manhattan(x, y) > k:
            return "impossible"
        
        dq = deque([(x, y, "")])
        while dq:
            cur_r, cur_c, cur_s = dq.popleft()
            
            if (cur_r, cur_c) == (r, c):
                if len(cur_s) == k:
                    return cur_s
                
                if (k - len(cur_s)) % 2:
                    return "impossible"
            
            for dr, dc, ds in dir:
                next_r, next_c = cur_r + dr, cur_c + dc
                
                if not is_safe(next_r, next_c, cur_s):
                    continue
                
                dq.append((next_r, next_c, cur_s + ds))
                break
        
        return "impossible"
    ```


### Java 풀이

- solution01

    ```java
    import java.util.*;
    
    class Solution {
        int r;
        int c;
        int k;
        int[][] board;
        
        public String solution(int n, int m, int x, int y, int r, int c, int k) {
            this.r = r;
            this.c = c;
            this.k = k;
            
            List<Node> dir = List.of(
                new Node(1, 0, "d"),
                new Node(0, -1, "l"),
                new Node(0, 1, "r"),
                new Node(-1, 0, "u")
            );
            
            board = new int[n + 2][m + 2];
            for (int row = 1; row <= n; row++) {
                for (int col = 1; col <= m; col++) {
                    board[row][col] = 1;
                }
            }
            
            // 메인 로직
            if (manhattan(x, y) > k) {
                return "impossible";
            }
            
            Deque<Node> dq = new ArrayDeque<>();
            dq.offer(new Node(x, y, ""));
            
            while (!dq.isEmpty()) {
                Node cur = dq.poll();
                
                if (cur.isArrive()) {
                    if (cur.step.length() == k) {
                        return cur.step;
                    }
                    
                    if ((k - cur.step.length()) % 2 != 0) {
                        return "impossible";
                    }
                }
                
                for (Node d : dir) {
                    int nextR = cur.row + d.row;
                    int nextC = cur.col + d.col;
                    
                    if (!isSafe(nextR, nextC, cur.step.length() + 1)) {
                        continue;
                    }
                    
                    dq.offer(new Node(nextR, nextC, cur.step + d.step));
                    break;
                }
            }
            
            return "impossible";
        }
        
        private int manhattan(int row, int col) {
            return Math.abs(r - row) + Math.abs(c - col);
        }
        
        private boolean isSafe(int row, int col, int step) {
            if (board[row][col] != 1) {
                return false;
            }
            
            if (manhattan(row, col) + step > k) {
                return false;
            }
            
            return true;
        }
        
        class Node {
            int row;
            int col;
            String step;
            
            public Node(int row, int col, String step) {
                this.row = row;
                this.col = col;
                this.step = step;
            }
            
            public boolean isArrive() {
                return row == r && col == c;
            }
        }
    }
    ```


---

## 🥰 배운점 & 느낀점

- 솔직히 문제 구현이 어렵지 않고 격자도 대충 봤는데 크기가 작은거 같아서 효율성은 생각도 못하고 무지성으로 코드를 짰다. 그래서 16분만에 구현 끝내고 제출했지만 결국 효율성을 충족하지 못해 문제를 풀지 못했다.
- 이제 어느정도 코드 구현은 할 수 있으니 시간/공간 효율성을 고려하는 훈련을 해야할거 같다. 그리고 manhattan 같이 좋은 아이디어는 정리해서 암기하고 체화할 필요가 있을거 같다.