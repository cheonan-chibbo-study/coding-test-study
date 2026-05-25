#include<vector>
#include<queue>
using namespace std;

int solution(vector<vector<int> > maps)
{
    int answer = 0;
    
    int n = maps.size();
    int m = maps[0].size();
    
    // 상 하 좌 우 구분
    int dx[4] = {-1,1,0,0};
    int dy[4] = {0,0,-1,1};
    
    queue<pair<int,int>> start;
    
    //시작점 지정
    start.push({0,0});
    
    while(!start.empty()){
        // 현재 좌표 꺼내기
        int x = start.front().first;
        int y = start.front().second;
        
        start.pop();
        
        for(int i = 0; i < 4; i++) {
        // 다음 좌표 계산
        int nx = x + dx[i];
        int ny = y + dy[i];
            
        // 범위 체크
        if(nx < 0 || ny <0 || nx>=n || ny>=m)
            continue;
            
        // 벽인지 체크
        if(maps[nx][ny] == 0)
            continue;
            
        // 방문 가능한지 체크
        if(maps[nx][ny]>1)
            continue;
            
        maps[nx][ny] = maps[x][y] + 1;
            
        // queue에 넣기
        start.push({nx,ny});
        }
    }
    answer = maps[n-1][m-1];
    
    if(answer == 1){
        return -1;
    }
    
    return answer;
}