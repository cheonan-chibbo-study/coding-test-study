# 문제

로봇(R)이 있는 위치에서 목표 지점(G)까지 이동하는 최소 이동 횟수를 구하는 문제이다.

단, 로봇은 상하좌우로 이동할 수 있지만 한 칸씩 움직이는 것이 아니라 벽(D)이나 맵의 경계에 부딪힐 때까지 계속 미끄러진다.

목표 지점에 도달할 수 없으면 -1을 반환한다.

# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/169199

# 문제 접근법

최소 이동 횟수를 구하는 문제이므로 BFS를 사용하였다.

일반적인 BFS와 다른 점은 한 칸씩 이동하는 것이 아니라 선택한 방향으로 계속 미끄러진 후 멈춘 위치를 다음 탐색 위치로 사용한다는 것이다.

탐색 과정

1. 시작 위치(R)를 찾는다.
2. BFS를 수행한다.
3. 현재 위치에서 상하좌우 4방향으로 이동한다.
4. 벽(D) 또는 맵 경계에 도달할 때까지 계속 이동한다.
5. 최종적으로 멈춘 위치를 큐에 넣는다.
6. 목표 지점(G)에 도착하면 이동 횟수를 반환한다.
7. BFS가 종료될 때까지 도착하지 못하면 -1을 반환한다.

# 소스코드

```cpp
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
```

# 새로 알게 된 점

* 최소 이동 횟수 문제는 BFS를 우선적으로 고려할 수 있다.
* BFS에서 이동 규칙만 바뀌어도 문제의 난이도가 크게 달라질 수 있다.
* 한 칸씩 이동하는 대신 벽에 부딪힐 때까지 이동하는 시뮬레이션을 BFS에 결합할 수 있다.
* 방문 처리는 실제로 멈춘 위치에 대해 수행해야 한다.

# 느낀점

처음에는 일반적인 게임 맵 최단거리 문제와 비슷해 보였지만, 한 칸씩 이동하는 것이 아니라 끝까지 미끄러지는 규칙 때문에 이동 위치를 계산하는 과정이 중요했다.

BFS 자체는 익숙했지만 이동 규칙을 정확하게 구현하는 것이 핵심이었고, BFS와 시뮬레이션을 함께 사용하는 방법을 익힐 수 있었다.