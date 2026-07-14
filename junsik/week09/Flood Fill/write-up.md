# LeetCode 733. Flood Fill

## 1. 문제

2차원 배열 `image`와 시작 좌표 `(sr, sc)`, 그리고 새로운 색상 `color`가 주어진다.

시작 좌표의 색과 **동일한 색을 가지며 상하좌우로 연결된 모든 칸**을 새로운 색으로 변경하는 문제이다.

---

## 2. 문제 링크

https://leetcode.com/problems/flood-fill/

---

## 3. 문제 접근법

처음에는 DP를 사용하는 문제라고 생각했지만, 각 칸의 값을 이전 결과를 이용해 계산하는 문제가 아니라 **연결된 영역을 탐색하는 문제**라는 점을 파악했다.

따라서 그래프 탐색 알고리즘인 **DFS**를 사용하였다.

풀이 과정은 다음과 같다.

1. 시작 위치의 원래 색(`originalColor`)을 저장한다.
2. 이미 변경하려는 색과 원래 색이 같다면 더 이상 탐색할 필요가 없으므로 바로 반환한다.
3. DFS를 수행하면서

   * 배열 범위를 벗어나면 종료한다.
   * 현재 칸의 색이 `originalColor`와 다르면 종료한다.
   * 현재 칸의 색을 `newColor`로 변경한다.
   * 상, 하, 좌, 우 네 방향을 재귀적으로 탐색한다.
4. 탐색이 끝나면 변경된 배열을 반환한다.

이 방법을 사용하면 시작 위치와 연결된 동일한 색의 영역만 모두 변경할 수 있다.

---

## 4. 소스코드

```cpp
class Solution {
public:
    void dfs(vector<vector<int>>& image, int r, int c, int originalColor, int newColor) {
        if (r < 0 || r >= image.size() || c < 0 || c >= image[0].size())
            return;

        if (image[r][c] != originalColor)
            return;

        image[r][c] = newColor;

        dfs(image, r - 1, c, originalColor, newColor);
        dfs(image, r + 1, c, originalColor, newColor);
        dfs(image, r, c - 1, originalColor, newColor);
        dfs(image, r, c + 1, originalColor, newColor);
    }

    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];

        if (originalColor == color)
            return image;

        dfs(image, sr, sc, originalColor, color);

        return image;
    }
};
```

---

## 5. 새로 알게 된 점

* Flood Fill은 DP 문제가 아니라 **그래프 탐색(DFS/BFS)** 문제라는 것을 알게 되었다.
* DFS에서는 방문 조건을 먼저 확인한 후 재귀 호출을 수행하는 것이 중요하다.
* 탐색 전에 원래 색과 변경할 색이 같은지 확인하지 않으면 불필요한 탐색이 발생할 수 있다.
* 연결 여부는 대각선이 아니라 상하좌우 네 방향만 고려한다.

---

## 6. 느낀점

DFS의 기본적인 구조를 익힐 수 있는 대표적인 문제였다. 처음에는 어떤 알고리즘을 사용해야 할지 고민했지만, 문제의 핵심이 "연결된 영역 탐색"이라는 것을 이해한 후에는 구현이 비교적 자연스럽게 진행되었다. 앞으로 비슷한 유형의 문제인 Number of Islands나 Max Area of Island를 풀면서 DFS와 BFS를 더욱 익혀보고 싶다.
