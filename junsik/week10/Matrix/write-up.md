# LeetCode 542. 01 Matrix

## 1. 문제

`m x n` 크기의 2차원 배열 `mat`이 주어진다.

배열에는 `0`과 `1`만 존재하며, 각 칸에 대해 **가장 가까운 `0`까지의 거리**를 구하여 새로운 2차원 배열로 반환하는 문제이다.

거리는 상하좌우로만 이동할 수 있으며, 인접한 칸으로 이동할 때마다 거리가 1씩 증가한다.

---

## 2. 문제 링크

https://leetcode.com/problems/01-matrix/

---

## 3. 문제 접근법

처음에는 각 `1`에서 가장 가까운 `0`을 찾기 위해 BFS를 수행하는 방법을 생각했지만, 모든 `1`마다 BFS를 실행하면 비효율적이라는 것을 알게 되었다.

대신 **모든 `0`을 시작점으로 하는 Multi-Source BFS**를 사용하였다.

풀이 과정은 다음과 같다.

1. `distance` 배열을 생성하고 모든 값을 `-1`로 초기화한다.
2. 배열 전체를 탐색하면서 `0`인 위치를 모두 Queue에 넣고 해당 위치의 거리를 `0`으로 설정한다.
3. Queue를 이용하여 BFS를 수행한다.
4. 현재 위치에서 상하좌우를 확인한다.
5. 범위 안에 있고 아직 방문하지 않은 칸(`distance == -1`)이라면

   * 현재 거리 + 1을 저장한다.
   * Queue에 넣어 다음 탐색을 진행한다.
6. BFS가 종료되면 `distance` 배열을 반환한다.

BFS의 특성상 먼저 방문한 경로가 가장 짧은 거리이므로, 처음 저장되는 값이 해당 칸의 최단 거리가 된다.

---

## 4. 소스코드

```cpp id="iv6k7k"
class Solution {
public:
    vector<vector<int>> updateMatrix(vector<vector<int>>& mat) {

        int n = mat.size();
        int m = mat[0].size();

        queue<pair<int, int>> q;
        vector<vector<int>> distance(n, vector<int>(m, -1));

        int dr[4] = {-1, 1, 0, 0};
        int dc[4] = {0, 0, -1, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    q.push({i, j});
                    distance[i][j] = 0;
                }
            }
        }

        while (!q.empty()) {

            pair<int, int> cur = q.front();
            q.pop();

            int r = cur.first;
            int c = cur.second;

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m)
                    continue;

                if (distance[nr][nc] == -1) {
                    distance[nr][nc] = distance[r][c] + 1;
                    q.push({nr, nc});
                }
            }
        }

        return distance;
    }
};
```

---

## 5. 새로 알게 된 점

* 모든 `1`에서 BFS를 시작하는 것보다, 모든 `0`을 시작점으로 하는 **Multi-Source BFS**가 훨씬 효율적이라는 것을 배웠다.
* `distance` 배열을 `-1`로 초기화하면 방문 여부와 최단 거리 저장을 하나의 배열로 동시에 관리할 수 있다.
* BFS에서는 먼저 방문한 경로가 항상 최단 거리이므로, 처음 저장한 거리가 정답이 된다.
* `Rotting Oranges`와 구조는 거의 같지만, 원본 배열을 수정하지 않고 별도의 `distance` 배열을 사용한다는 차이를 이해했다.

---

## 6. 느낀점

이번 문제는 `Rotting Oranges`와 매우 비슷한 구조여서 이전에 배운 Multi-Source BFS를 그대로 적용할 수 있었다. 처음에는 각 `1`에서 BFS를 시작해야 한다고 생각했지만, 모든 `0`을 Queue에 넣고 동시에 탐색하는 방식이 훨씬 효율적이라는 것을 이해할 수 있었다. 또한 방문 배열을 따로 만들지 않고 `distance` 배열 하나로 방문 여부와 최단 거리를 함께 관리하는 방법도 새롭게 배울 수 있었다. 앞으로 최단 거리 문제를 만나면 BFS를 우선적으로 떠올릴 수 있을 것 같다.
