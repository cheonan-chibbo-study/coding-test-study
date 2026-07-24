class Solution {
public:
    void solve(vector<vector<char>>& board) {
        //행 검사
        for(int i = 0; i<board.size(); i++){
            if(board[i][0] == 'O')
                DFS(board,i,0);
            if (board[i][board[0].size() - 1] == 'O')
                DFS(board, i, board[0].size() - 1);
        }
        //열 검사
        for(int j = 0; j<board[0].size(); j++){
                if(board[0][j] == 'O')
                    DFS(board,0,j);
                if(board[board.size() - 1][j] == 'O')
                    DFS(board, board.size() - 1, j);
            }

        for(int i = 0; i<board.size(); i++){
            for(int j = 0; j<board[0].size(); j++){
                if(board[i][j] == 'O')
                    board[i][j] = 'X';

                if(board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    void DFS(vector<vector<char>> &board, int r, int c){
        if (r < 0 || r >= board.size() 
        || c < 0 || c >= board[0].size())
            return;

        if (board[r][c] != 'O')
            return;

        board[r][c] = '#';

        DFS(board, r - 1, c);
        DFS(board, r + 1, c);
        DFS(board, r, c - 1);
        DFS(board, r, c + 1);
    }
};