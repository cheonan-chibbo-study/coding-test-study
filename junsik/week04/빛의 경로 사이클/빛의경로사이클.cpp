#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<string> grid) {
    vector<int> answer;
    int row = grid.size();
    int col = grid[0].size();
    
    int dx[4] = {-1, 0, 1, 0};
    int dy[4] = { 0, 1, 0,-1};
    
    vector<vector<vector<bool>>> visited(
        row,
        vector<vector<bool>>(col, vector<bool>(4, false))
    );
    
    for(int r = 0; r < row; r++)
    {
        for(int c = 0; c < col; c++)
        {
            for(int dir = 0; dir < 4; dir++)
            {
                if(visited[r][c][dir])
                    continue;

                int cnt = 0;   

                int cr = r;
                int cc = c;
                int cdir = dir;

                while(!visited[cr][cc][cdir])
                {
                    visited[cr][cc][cdir] = true;
                    cnt++;

                    if(grid[cr][cc] == 'L')
                        cdir = (cdir + 3) % 4;
                    if(grid[cr][cc] == 'R')
                        cdir = (cdir + 1) % 4;
                    
                    cr += dx[cdir];
                    cc += dy[cdir];

                    cr = (cr + row) % row;
                    cc = (cc + col) % col;
                }
                answer.push_back(cnt);
            }
        }
    }
    sort(answer.begin(), answer.end());
    
    return answer;
}