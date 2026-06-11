#include <string>
#include <vector>
#include <queue>

using namespace std;

int BFS (int startX, int startY, char target, vector<string>& maps){
    int n = maps.size();
    int m = maps[0].size();
    
    vector<vector<bool>> visited(n, vector<bool>(m,false));
    
    queue<pair<pair<int,int>,int>> q;
    
    q.push({{startX, startY},0});
    visited[startX][startY] = true;
    
    int dx[4] = {-1,1,0,0};
    int dy[4] = {0,0,-1,1};
    
    while(!q.empty()){
        int x = q.front().first.first;
        int y = q.front().first.second;
        int dist = q.front().second;
        q.pop();
        
        if(maps[x][y] == target)
            return dist;
        
        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx<0 || ny < 0 || nx >= n || ny >= m)
                continue;
            
            if(maps[nx][ny] == 'X')
                continue;
            
            if(visited[nx][ny])
                continue;
            
            visited[nx][ny] = true;
            
            q.push({{nx,ny}, dist + 1});
        }
    }
    
    return -1;
}

int solution(vector<string> maps) {
    int answer = 0;
    int startX = 0;
    int startY = 0;
    int leverX = 0;
    int leverY = 0;
    
    for(int i = 0; i < maps.size(); i++)
    {
        for(int j = 0; j < maps[0].size(); j++)
        {
            if(maps[i][j] == 'S')
            {
                startX = i;
                startY = j;
            }

            if(maps[i][j] == 'L')
            {
                leverX = i;
                leverY = j;
            }
        }
    }
    
    int d1 = BFS(startX, startY,'L',maps);
    int d2 = BFS(leverX, leverY, 'E',maps);
    
    if(d1 == -1 ||d2 == -1)
        return -1;
    
    answer = d1+d2;
    return answer;
}