class Solution {
public:
    int dr[4] = {-1, 1, 0, 0};
    int dc[4] = {0, 0, -1, 1};

    bool exist(vector<vector<char>>& board, string word) {
        for (int i = 0; i<board.size(); i++){
            for (int j = 0; j<board[0].size(); j++)
            {
                if(board[i][j] == word[0]){
                    if(DFS(board,word,i,j,0))
                        return true;
                }
            }
        }
        return false;
    }

    bool DFS(vector<vector<char>>& board, string& word, int r, int c, int index) {

        // 단어를 모두 찾은 경우
        if (index == word.size())
            return true;

        // 범위를 벗어난 경우
        if (r < 0 || r >= board.size() || c < 0 || c >= board[0].size())
            return false;

        // 문자가 다르면 실패
        if (board[r][c] != word[index])
            return false;

        // 현재 문자 저장 후 방문 처리
        char temp = board[r][c];
        board[r][c] = '#';

        // 상하좌우 탐색
        for (int d = 0; d < 4; d++) {

            int nr = r + dr[d];
            int nc = c + dc[d];

            if (DFS(board, word, nr, nc, index + 1))
                return true;
        }

        // 백트래킹 (원상복구)
        board[r][c] = temp;

        return false;
    }
};