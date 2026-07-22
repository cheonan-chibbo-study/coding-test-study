# LeetCode 1926. Nearest Exit from Entrance in Maze

## 1. 문제

`m x n` 크기의 미로 `maze`가 주어진다.

* `'.'` : 이동 가능한 칸
* `'+'` : 벽

또한 시작 위치인 `entrance`가 주어진다.

상하좌우로만 이동할 수 있으며, **가장 가까운 출구까지의 최소 이동 횟수**를 구하는 문제이다.

출구는 **미로의 가장자리에 있는 이동 가능한 칸**을 의미하지만, **시작점이 가장자리에 있더라도 출구로는 인정하지 않는다.**

출구에 도달할 수 없다면 `-1`을 반환한다.

---

## 2. 문제 링크

https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/

---

## 3. 문제 접근법

이 문제는 시작점에서 가장 가까운 출구까지의 **최단 거리**를 구하는 문제이므로 BFS를 사용하였다.

풀이 과정은 다음과 같다.

1. Queue에 시작점(`entrance`)과 현재 이동 거리(`0`)를 함께 저장한다.
2. 방문 여부를 확인하기 위해 `visited` 배열을 생성한다.
3. BFS를 수행하면서 현재 위치를 Queue에서 꺼낸다.
4. 현재 위치가 가장자리이고 시작점이 아니라면 가장 가까운 출구이므로 현재 이동 거리를 반환한다.
5. 상하좌우를 확인한다.
6. 범위를 벗어나거나 벽(`'+'`)이거나 이미 방문한 칸이면 건너뛴다.
7. 이동 가능한 칸이라면 방문 처리 후 거리(`현재 거리 + 1`)와 함께 Queue에 넣는다.
8. Queue가 모두 비워질 때까지 출구를 찾지 못하면 `-1`을 반환한다.

BFS는 가까운 위치부터 탐색하므로 처음 발견한 출구가 항상 가장 가까운 출구가 된다.

---

## 4. 소스코드

```cpp id="w6gnlm"
class Solution {
public:
    int nearestExit(vector<vector<char>>& maze, vector<int>& entrance) {

        int n = maze.size();
        int m = maze[0].size();

        queue<tuple<int, int, int>> q;
        vector<vector<bool>> visited(n, vector<bool>(m, false));

        int dr[4] = {-1, 1, 0, 0};
        int dc[4] = {0, 0, -1, 1};

        q.push({entrance[0], entrance[1], 0});
        visited[entrance[0]][entrance[1]] = true;

        while (!q.empty()) {

            auto cur = q.front();
            q.pop();

            int r = get<0>(cur);
            int c = get<1>(cur);
            int dist = get<2>(cur);

            if ((r == 0 || r == n - 1 || c == 0 || c == m - 1) &&
                !(r == entrance[0] && c == entrance[1])) {
                return dist;
            }

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m)
                    continue;

                if (maze[nr][nc] == '+')
                    continue;

                if (visited[nr][nc])
                    continue;

                visited[nr][nc] = true;
                q.push({nr, nc, dist + 1});
            }
        }

        return -1;
    }
};
```

---

## 5. 새로 알게 된 점

* BFS는 최단 거리를 구하는 문제에서 가장 적합한 알고리즘이라는 것을 다시 확인하였다.
* `queue<tuple<int, int, int>>`를 사용하면 좌표와 현재 이동 거리를 함께 저장할 수 있어 별도의 거리 배열 없이 구현할 수 있었다.
* 출구는 가장자리에 있는 이동 가능한 칸이지만, 시작점은 출구로 인정하지 않는 조건을 반드시 함께 확인해야 한다.
* `visited` 배열을 이용해 이미 방문한 칸을 다시 탐색하지 않도록 구현할 수 있었다.

---

## 6. 느낀점

이번 문제를 통해 BFS를 이용한 최단 거리 탐색을 연습할 수 있었다. 이전에 풀었던 `Rotting Oranges`와 `01 Matrix`는 여러 시작점에서 동시에 탐색하는 Multi-Source BFS였지만, 이번 문제는 시작점 하나에서 출발하는 Single-Source BFS라는 차이를 이해할 수 있었다. 또한 Queue에 거리까지 함께 저장하는 `tuple` 사용법도 익힐 수 있었고, BFS에서는 처음 도착한 경로가 최단 경로라는 점을 다시 확인할 수 있었다.
