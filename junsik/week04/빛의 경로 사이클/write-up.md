# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/86052

# 문제 접근법

처음에는 단순히 특정 위치로 다시 돌아오는 경우를 사이클이라고 생각했지만, 문제를 자세히 읽어보니 위치뿐만 아니라 방향까지 동일해야 같은 상태라는 점이 중요했다.
정확하게 어떤 자료형을 써야할지 몰라서 gpt한테 물어보니 3차원배열을 써서 정리하라고 알려줬다.
따라서 하나의 상태를 다음과 같이 정의하였다.

* 행(row)
* 열(col)
* 방향(dir)

즉 `(행, 열, 방향)` 을 하나의 상태로 보고 방문 여부를 관리하였다.

방문 배열은 3차원 배열을 사용하였다.

```cpp
visited[row][col][dir]
```

현재 위치와 방향이 방문되지 않은 상태라면 해당 상태를 시작점으로 설정하고 시뮬레이션을 진행하였다.

시뮬레이션 과정

1. 현재 상태 방문 처리
2. 사이클 길이 증가
3. 현재 칸의 문자(S, L, R)에 따라 방향 변경
4. 방향에 따라 다음 칸 이동
5. 격자를 벗어나면 반대편으로 이동
6. 이미 방문한 상태를 만나면 종료

하나의 사이클이 종료될 때마다 길이를 저장하고, 모든 상태를 탐색한 뒤 오름차순 정렬하여 반환하였다.

# 소스코드

```cpp
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
```

# 새로 알게 된 점

* 위치만이 아니라 방향까지 포함하여 상태를 관리해야 하는 경우가 있다.
* 3차원 방문 배열을 사용하는 방법을 익혔다.
* 격자 밖으로 나갔을 때 반대편으로 이동하는 구조는 모듈러 연산(`%`)을 이용해 구현할 수 있다.
* 사이클 탐색 문제는 DFS/BFS가 아닌 단순 시뮬레이션으로도 해결할 수 있다.
* 반복문 내부에서 `sort()`를 수행하면 불필요한 연산이 크게 증가할 수 있다.

# 느낀점

처음 문제를 읽었을 때는 사이클의 의미가 잘 이해되지 않았고, 방향까지 상태에 포함된다는 점도 바로 떠올리지 못했다.

하지만 `(행, 열, 방향)`을 하나의 상태로 생각하니 문제 구조가 훨씬 명확해졌다.

또한 3차원 방문 배열과 모듈러 연산을 활용한 이동 처리 방법을 익힐 수 있었고, 구현 문제를 단계적으로 분해하여 해결하는 연습이 된 문제였다.