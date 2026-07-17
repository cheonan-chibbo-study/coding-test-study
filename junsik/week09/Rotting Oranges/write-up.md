# LeetCode 994. Rotting Oranges

## 1. 문제

`m x n` 크기의 2차원 배열 `grid`가 주어진다.

* `0` : 빈 칸
* `1` : 신선한 오렌지
* `2` : 썩은 오렌지

매 1분마다 썩은 오렌지와 상하좌우로 인접한 신선한 오렌지가 함께 썩는다.

모든 신선한 오렌지가 썩는 데 걸리는 최소 시간을 구하고, 끝까지 썩지 못하는 오렌지가 존재하면 `-1`을 반환하는 문제이다.

---

## 2. 문제 링크

https://leetcode.com/problems/rotting-oranges/

---

## 3. 문제 접근법

처음에는 DFS처럼 접근하려고 했지만, 문제의 핵심이 **모든 썩은 오렌지가 동시에 주변으로 퍼진다**는 점이라는 것을 확인하고 BFS를 사용하였다.

풀이 과정은 다음과 같다.

1. 배열 전체를 탐색하면서 처음부터 썩어 있는 오렌지(`2`)의 위치를 모두 Queue에 저장한다.
2. 동시에 신선한 오렌지(`1`)의 개수를 `fresh` 변수에 저장한다.
3. Queue가 비어 있지 않고 신선한 오렌지가 남아 있는 동안 BFS를 수행한다.
4. 현재 Queue의 크기를 저장하여 **현재 분(minute)에 썩어 있는 오렌지들만 처리**한다.
5. Queue에서 하나씩 꺼내 상하좌우를 확인한다.
6. 범위 안에 있고 신선한 오렌지라면

   * `2`로 변경하여 썩게 만든다.
   * Queue에 넣어 다음 분에 처리한다.
   * `fresh`를 1 감소시킨다.
7. 현재 레벨의 탐색이 끝나면 `minute`를 1 증가시킨다.
8. BFS가 종료된 후 `fresh`가 0이면 `minute`를 반환하고, 그렇지 않으면 `-1`을 반환한다.

이 문제에서는 Queue의 크기를 먼저 저장한 뒤 해당 개수만큼만 처리하여 한 번의 반복이 정확히 **1분**을 의미하도록 구현하였다.

---

## 4. 소스코드

```cpp
class Solution {
public:
    int orangesRotting(vector<vector<int>>& grid) {
        queue<pair<int,int>> q;
        int fresh = 0;
        int minute = 0;

        int dr[4] = {-1, 1, 0, 0};
        int dc[4] = {0, 0, -1, 1};

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid[0].size(); j++) {
                if (grid[i][j] == 2)
                    q.push({i, j});
                else if (grid[i][j] == 1)
                    fresh++;
            }
        }

        while (!q.empty() && fresh > 0) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                auto cur = q.front();
                q.pop();

                int r = cur.first;
                int c = cur.second;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= grid.size() ||
                        nc < 0 || nc >= grid[0].size())
                        continue;

                    if (grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        q.push({nr, nc});
                        fresh--;
                    }
                }
            }

            minute++;
        }

        return fresh == 0 ? minute : -1;
    }
};
```

---

## 5. 새로 알게 된 점

* BFS에서는 Queue의 현재 크기를 저장하여 한 레벨씩 탐색하면 시간의 흐름을 자연스럽게 표현할 수 있다.
* 여러 개의 시작점에서 동시에 탐색을 시작하는 **Multi-Source BFS**를 사용할 수 있다는 것을 배웠다.
* DFS와 달리 BFS에서는 재귀 호출 대신 Queue를 이용해 다음에 탐색할 위치를 관리한다.
* 상하좌우 탐색 방식은 DFS와 동일하지만, 재귀 호출 대신 Queue에 좌표를 추가하는 점이 가장 큰 차이였다.
* 배열을 탐색할 때는 항상 범위 확인을 먼저 수행해야 런타임 에러를 방지할 수 있다.

---

## 6. 느낀점

Flood Fill, Number of Islands, Max Area of Island까지는 DFS를 사용하여 연결된 영역을 탐색하는 문제였다. 이번 문제를 풀면서 시간의 흐름과 동시에 퍼지는 상황은 BFS가 훨씬 적합하다는 것을 이해할 수 있었다. 특히 Queue의 크기를 기준으로 한 레벨씩 탐색하는 방법이 처음에는 낯설었지만, 한 번의 반복이 1분을 의미한다는 개념을 이해한 후에는 BFS의 동작 원리를 명확하게 파악할 수 있었다. 앞으로 최단 거리나 전파, 시간 계산이 포함된 문제에서는 BFS를 먼저 떠올릴 수 있을 것 같다.
