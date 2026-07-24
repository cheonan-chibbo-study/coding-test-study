# LeetCode 130. Surrounded Regions

## 1. 문제

`m x n` 크기의 2차원 배열 `board`가 주어진다.

배열에는 `'X'`와 `'O'`만 존재하며, **상하좌우로 연결된 `'O'` 중 `'X'`로 완전히 둘러싸인 영역을 모두 `'X'`로 변경**해야 한다.

단, **가장자리(Border)에 있는 `'O'`와 가장자리 `'O'`에 연결된 `'O'`는 변경하지 않는다.**

---

## 2. 문제 링크

https://leetcode.com/problems/surrounded-regions/

---

## 3. 문제 접근법

처음에는 `'O'`가 둘러싸였는지를 하나씩 검사하려고 생각했지만, 모든 `'O'`를 확인하면 구현이 복잡해진다.

반대로 **바꾸면 안 되는 `'O'`를 먼저 찾는 방식**으로 접근하였다.

풀이 과정은 다음과 같다.

1. 첫 번째 행, 마지막 행, 첫 번째 열, 마지막 열을 모두 탐색한다.
2. 가장자리에서 `'O'`를 발견하면 DFS를 수행한다.
3. DFS를 통해 가장자리와 연결된 모든 `'O'`를 `'#'`로 변경하여 보호 표시를 한다.
4. DFS가 끝난 후 배열 전체를 다시 탐색한다.
5. 아직 `'O'`인 칸은 모두 둘러싸인 영역이므로 `'X'`로 변경한다.
6. `'#'`로 표시했던 칸은 원래 안전한 영역이므로 다시 `'O'`로 복구한다.

이 문제의 핵심은 **삭제할 영역을 찾는 것이 아니라, 삭제하면 안 되는 영역을 먼저 표시하는 것**이다.

---

## 4. 소스코드

```cpp
class Solution {
public:
    void DFS(vector<vector<char>>& board, int r, int c) {

        if (r < 0 || r >= board.size() ||
            c < 0 || c >= board[0].size())
            return;

        if (board[r][c] != 'O')
            return;

        board[r][c] = '#';

        DFS(board, r - 1, c);
        DFS(board, r + 1, c);
        DFS(board, r, c - 1);
        DFS(board, r, c + 1);
    }

    void solve(vector<vector<char>>& board) {

        if (board.empty())
            return;

        // 첫 번째 열, 마지막 열
        for (int i = 0; i < board.size(); i++) {
            if (board[i][0] == 'O')
                DFS(board, i, 0);

            if (board[i][board[0].size() - 1] == 'O')
                DFS(board, i, board[0].size() - 1);
        }

        // 첫 번째 행, 마지막 행
        for (int j = 0; j < board[0].size(); j++) {
            if (board[0][j] == 'O')
                DFS(board, 0, j);

            if (board[board.size() - 1][j] == 'O')
                DFS(board, board.size() - 1, j);
        }

        // 결과 변환
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board[0].size(); j++) {

                if (board[i][j] == 'O')
                    board[i][j] = 'X';

                else if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }
};
```

---

## 5. 새로 알게 된 점

* 모든 `'O'`를 검사하는 것이 아니라, **가장자리와 연결된 `'O'`를 먼저 찾는 방식**이 더 효율적이라는 것을 배웠다.
* DFS를 이용하여 안전한 영역을 `'#'`로 임시 표시한 뒤, 마지막에 한 번에 변환하는 **Marking 기법**을 익혔다.
* 임시 문자인 `'#'`는 입력에 등장하지 않는 문자이므로 안전한 영역을 구분하는 데 사용할 수 있었다.
* DFS 자체는 `Number of Islands`와 거의 동일하지만, 방문 표시를 `'#'`로 하고 마지막에 원래 문자로 복구하는 점이 다르다는 것을 이해했다.

---

## 6. 느낀점

이번 문제를 통해 DFS를 조금 더 응용하는 방법을 배울 수 있었다. 처음에는 둘러싸인 영역을 직접 찾으려고 했지만, 반대로 가장자리와 연결된 안전한 영역을 먼저 표시하는 방식이 훨씬 간단하고 효율적이라는 것을 알게 되었다. 또한 임시 문자를 이용한 Marking 기법은 앞으로 다양한 Grid 문제에서도 활용할 수 있는 유용한 아이디어라고 느꼈다.
