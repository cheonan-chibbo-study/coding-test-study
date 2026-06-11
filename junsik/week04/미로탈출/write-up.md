# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/159993

# 문제 접근법

처음에는 게임 맵 최단거리 문제와 동일하게 생각했지만, 이 문제는 반드시 레버를 거쳐야 한다는 조건이 있었다.

따라서 최단 경로를 한 번 구하는 것이 아니라 다음 두 구간의 최단 거리를 각각 구해야 했다.

```text
시작점(S) → 레버(L)

레버(L) → 출구(E)
```

최단 거리를 구하는 문제이므로 BFS를 사용하였다.

BFS 함수는 시작 좌표와 목표 문자를 받아 목표 지점까지의 최단 거리를 반환하도록 구현하였다.

탐색 과정은 다음과 같다.

1. 시작 위치를 큐에 삽입
2. 방문 배열 초기화
3. 현재 위치에서 상하좌우 탐색
4. 범위를 벗어나거나 벽(X)이면 이동 불가
5. 방문하지 않은 위치라면 큐에 삽입
6. 목표 문자에 도달하면 현재 거리 반환
7. 탐색 종료 시 도달하지 못하면 -1 반환

이후

```text
S → L
```

거리와

```text
L → E
```

거리를 각각 구하여 합산하였다.

둘 중 하나라도 -1이라면 최종적으로 -1을 반환하였다.

# 소스코드

```cpp
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
```

# 새로 알게 된 점

* BFS는 최단 거리를 구하는 대표적인 알고리즘이다.
* 하나의 문제 안에서 BFS를 여러 번 사용할 수 있다.
* 목표 지점을 고정하지 않고 목표 문자를 매개변수로 전달하여 BFS 함수를 재사용할 수 있다.
* 방문 배열은 BFS를 수행할 때마다 새롭게 초기화해야 한다.

# 느낀점

게임 맵 최단거리와 매우 비슷한 문제라고 생각했지만, 반드시 레버를 거쳐야 한다는 조건 때문에 BFS를 두 번 수행해야 했다.

처음에는 하나의 BFS로 해결하려고 했지만 문제를 다시 분석하면서 구간을 나누어 생각하니 해결할 수 있었다.

또한 BFS 함수를 분리하여 재사용하는 방법을 익힐 수 있었고, 최단 거리 문제에서 BFS를 더욱 자연스럽게 적용할 수 있게 되었다.