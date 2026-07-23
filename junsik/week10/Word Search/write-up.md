# LeetCode 79. Word Search

## 1. 문제

`m x n` 크기의 문자 배열 `board`와 문자열 `word`가 주어진다.

배열에서 상하좌우로만 이동하여 `word`를 만들 수 있으면 `true`, 만들 수 없으면 `false`를 반환하는 문제이다.

단, **같은 칸은 한 번의 탐색에서 두 번 사용할 수 없다.**

---

## 2. 문제 링크

https://leetcode.com/problems/word-search/

---

## 3. 문제 접근법

이 문제는 하나의 경로를 끝까지 탐색해야 하므로 DFS를 사용하였다. 하지만 이전에 풀었던 DFS 문제들과 달리, 탐색에 실패했을 경우 다른 경로를 다시 시도해야 하므로 **Backtracking** 기법이 필요했다.

풀이 과정은 다음과 같다.

1. 배열 전체를 탐색하며 단어의 첫 글자와 같은 칸을 찾는다.
2. 첫 글자를 찾으면 DFS를 시작한다.
3. 현재 위치가 단어의 현재 문자와 일치하는지 확인한다.
4. 현재 칸을 방문 처리하기 위해 임시로 `'#'`로 변경한다.
5. 상하좌우 네 방향으로 다음 문자를 찾기 위해 DFS를 수행한다.
6. 한 방향이라도 단어를 끝까지 찾으면 `true`를 반환한다.
7. 모든 방향이 실패하면 현재 칸을 원래 문자로 복구한 후 `false`를 반환한다.
8. 모든 시작 위치를 탐색해도 단어를 만들 수 없으면 `false`를 반환한다.

이 문제의 핵심은 **방문 처리 후 탐색을 진행하고, 실패하면 원래 상태로 복구하는 Backtracking**이다.

---

## 4. 소스코드

```cpp
class Solution {
public:
    int dr[4] = {-1, 1, 0, 0};
    int dc[4] = {0, 0, -1, 1};

    bool dfs(vector<vector<char>>& board, string& word, int r, int c, int index) {

        if (index == word.size())
            return true;

        if (r < 0 || r >= board.size() || c < 0 || c >= board[0].size())
            return false;

        if (board[r][c] != word[index])
            return false;

        char temp = board[r][c];
        board[r][c] = '#';

        for (int d = 0; d < 4; d++) {

            int nr = r + dr[d];
            int nc = c + dc[d];

            if (dfs(board, word, nr, nc, index + 1))
                return true;
        }

        board[r][c] = temp;

        return false;
    }

    bool exist(vector<vector<char>>& board, string word) {

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board[0].size(); j++) {

                if (board[i][j] == word[0]) {
                    if (dfs(board, word, i, j, 0))
                        return true;
                }
            }
        }

        return false;
    }
};
```

---

## 5. 새로 알게 된 점

* 이전 DFS 문제들과 달리 탐색에 실패했을 경우 다른 경로를 시도하기 위해 **Backtracking**이 필요하다는 것을 배웠다.
* 방문 여부를 저장하기 위해 `visited` 배열 대신 현재 문자를 `'#'`로 변경한 뒤, 탐색이 끝나면 원래 문자로 복구하는 방법을 사용할 수 있다는 것을 알게 되었다.
* DFS 함수에서 `index`를 이용하여 현재 단어의 몇 번째 문자를 찾고 있는지 관리할 수 있었다.
* 한 경로에서 단어를 모두 찾으면 즉시 `true`를 반환하여 불필요한 탐색을 줄일 수 있었다.

---

## 6. 느낀점

이번 문제를 통해 처음으로 Backtracking 기법을 사용해 보았다. 이전까지의 DFS는 방문한 칸을 다시 사용할 필요가 없었지만, 이번에는 한 경로가 실패하면 다른 경로를 다시 탐색해야 했기 때문에 방문한 칸을 원래 상태로 복구하는 과정이 중요했다. 또한 DFS를 응용하여 문자열을 순서대로 탐색하는 방법을 익힐 수 있었고, 앞으로 조합, 순열, N-Queens와 같은 Backtracking 문제를 해결하는 데 도움이 될 것이라고 생각한다.
