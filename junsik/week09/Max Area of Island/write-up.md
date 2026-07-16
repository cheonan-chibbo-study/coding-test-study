# LeetCode 695. Max Area of Island

## 1. 문제

`m x n` 크기의 2차원 배열 `grid`가 주어진다.

* `1` : 육지(Land)
* `0` : 바다(Water)

상하좌우로 연결된 육지를 하나의 섬(Island)으로 간주할 때, 가장 넓은 섬의 넓이(칸의 개수)를 구하는 문제이다. 섬이 존재하지 않으면 `0`을 반환한다.

---

## 2. 문제 링크

https://leetcode.com/problems/max-area-of-island/

---

## 3. 문제 접근법

이전 문제인 Number of Islands와 동일하게 DFS를 이용하여 해결하였다. 차이점은 섬의 개수를 세는 것이 아니라, DFS를 수행하면서 하나의 섬이 차지하는 칸의 개수를 계산해야 한다는 것이다.

풀이 과정은 다음과 같다.

1. 배열 전체를 순회한다.
2. 현재 위치가 `1`이라면 새로운 섬을 발견한 것이므로 DFS를 수행한다.
3. DFS에서는 현재 칸을 방문 처리한 후 상, 하, 좌, 우 네 방향을 재귀적으로 탐색한다.
4. 현재 칸을 포함한 넓이를 계산하기 위해 `1 + 상 + 하 + 좌 + 우`의 결과를 반환한다.
5. DFS가 반환한 넓이와 현재 최대 넓이를 비교하여 더 큰 값을 저장한다.
6. 모든 탐색이 끝나면 가장 큰 섬의 넓이를 반환한다.

DFS의 종료 조건은 다음과 같다.

* 배열 범위를 벗어난 경우
* 현재 위치가 바다(`0`)이거나 이미 방문한 경우

이 경우에는 더 이상 탐색할 수 없으므로 `0`을 반환한다.

---

## 4. 소스코드

```cpp
class Solution {
public:
    int dfs(vector<vector<int>>& grid, int r, int c) {
        if (r < 0 || r >= grid.size() || c < 0 || c >= grid[0].size())
            return 0;

        if (grid[r][c] == 0)
            return 0;

        grid[r][c] = 0;

        return 1
             + dfs(grid, r - 1, c)
             + dfs(grid, r + 1, c)
             + dfs(grid, r, c - 1)
             + dfs(grid, r, c + 1);
    }

    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int maxArea = 0;

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid[0].size(); j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    maxArea = max(maxArea, area);
                }
            }
        }

        return maxArea;
    }
};
```

---

## 5. 새로 알게 된 점

* 이전 문제들과 달리 DFS가 단순히 탐색만 하는 것이 아니라, **현재 섬의 넓이를 반환**하도록 구현할 수 있다는 것을 알게 되었다.
* 재귀 호출의 반환값을 이용해 `현재 칸(1) + 네 방향의 넓이`를 계산하는 방식이 매우 유용하다는 것을 이해했다.
* 방문 배열을 별도로 만들지 않고, 방문한 육지를 `0`으로 변경하여 중복 탐색을 방지할 수 있다.
* 같은 DFS라도 문제의 요구사항에 따라 `void` 대신 `int`를 반환하도록 설계할 수 있다는 점을 배웠다.

---

## 6. 느낀점

Flood Fill과 Number of Islands를 먼저 풀고 나니 전체적인 DFS 구조는 익숙했지만, 이번 문제에서는 DFS가 값을 반환한다는 점이 가장 큰 차이였다. 처음에는 DFS가 단순히 방문 처리만 하는 함수라고 생각했지만, 재귀 호출의 반환값을 이용해 섬의 넓이를 계산하는 방식을 이해하면서 DFS를 더 다양하게 활용할 수 있다는 것을 배웠다. 비슷한 형태의 그래프 탐색 문제를 계속 풀면서 DFS와 BFS에 더욱 익숙해지고 싶다.
