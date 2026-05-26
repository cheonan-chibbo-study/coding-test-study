# 문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/1844

## 문제 접근법
1. 게임 맵이 2차원 배열로 주어지고 1이면 지나갈수있는 길, 0이면 벽으로 간주된다.
2. 캐릭터는 0,0에서 시작하며, 목적지까지 도달하기 위해 최단 거리를 구하는 문제이다.
3. 따라서 이 문제는 BFS를 사용하여 풀어야할 문제이다.

---

### 문제 해결
1. 이에 대한 지식이 부족하여 GPT를 이용하여 이 문제의 흐름을 파악했다.
```cpp
queue<pair<int,int>> q;

// 시작점 넣기

while(!q.empty()) {

    // 현재 좌표 꺼내기

    for(int i = 0; i < 4; i++) {

        // 다음 좌표 계산

        // 범위 체크

        // 벽인지 체크

        // 방문 가능한지 체크

        // 거리 저장

        // queue에 넣기
    }
}
```
2. 필자가 채워야할 부분을 주석처리를 이용하여 코드의 전체적인 흐름을 파악했다.
```cpp
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
```

---
## 느낀점
DFS에 관하여 지식이 부족한점 있어서, 이에 관해 공부의 필요성을 느꼈고, GPT를 쓰지않아도 전체적인 흐름을 쓸수있게끔 더욱 공부해야겠다고 느꼈다.