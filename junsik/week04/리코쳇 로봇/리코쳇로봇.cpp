#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(vector<string> board) {
    int row = board.size();
    int col = board[0].size();

    int sx = 0;
    int sy = 0;

    for(int i = 0; i < row; i++) {
        for(int j = 0; j < col; j++) {
            if(board[i][j] == 'R') {
                sx = i;
                sy = j;
            }
        }
    }

    vector<vector<bool>> visited(row, vector<bool>(col, false));

    queue<pair<pair<int,int>, int>> q;

    q.push({{sx, sy}, 0});
    visited[sx][sy] = true;

    int dx[4] = {-1, 1, 0, 0};
    int dy[4] = {0, 0, -1, 1};

    while(!q.empty()) {

        int x = q.front().first.first;
        int y = q.front().first.second;
        int dist = q.front().second;
        q.pop();

        if(board[x][y] == 'G')
            return dist;

        for(int d = 0; d < 4; d++) {

            int nx = x;
            int ny = y;

            while(true) {

                int tx = nx + dx[d];
                int ty = ny + dy[d];

                if(tx < 0 || tx >= row ||
                   ty < 0 || ty >= col ||
                   board[tx][ty] == 'D')
                    break;

                nx = tx;
                ny = ty;
            }

            if(!visited[nx][ny]) {
                visited[nx][ny] = true;
                q.push({{nx, ny}, dist + 1});
            }
        }
    }

    return -1;
}