# 문제 링크

https://school.programmers.co.kr/learn/courses/30/lessons/87946

# 문제 접근법

* 각 던전은 최소 필요 피로도와 소모 피로도를 가진다. 던전을 탐험할 수 있는 순서는 정해져 있지 않으므로 모든 경우의 수를 탐색해야 한다.

* 처음에는 현재 피로도로 입장 가능한 던전을 선택하고, 해당 던전을 방문한 후 남은 피로도로 다시 탐험 가능한 던전을 찾는 방식으로 접근하였다. 이를 위해 DFS(깊이 우선 탐색)와 백트래킹을 사용하였다.

* visited 배열을 이용하여 이미 방문한 던전을 체크하고, 현재 피로도로 입장 가능한 던전만 탐색한다. 탐색할 때마다 방문한 던전 수를 증가시키고, 현재까지 방문한 최대 던전 수를 answer에 저장한다.

* 탐색이 끝난 후에는 방문 여부를 다시 false로 변경하여 다른 경우의 수를 탐색할 수 있도록 하였다.

# 소스코드

```cpp
#include <string>
#include <vector>

using namespace std;

int answer = 0;
bool visited[8];

void DFS(int k, vector<vector<int>>& dungeons, int count)
{
    answer = max(answer, count);

    for(int i = 0; i < dungeons.size(); i++)
    {
        if(visited[i])
            continue;

        if(k < dungeons[i][0])
            continue;

        visited[i] = true;

        DFS(
            k - dungeons[i][1],
            dungeons,
            count + 1
        );

        visited[i] = false;
    }
}

int solution(int k, vector<vector<int>> dungeons)
{
    DFS(k, dungeons, 0);

    return answer;
}
```

# 새로 알게 된 점

* 이 문제를 통해 DFS와 백트래킹을 활용하여 모든 경우의 수를 탐색하는 방법을 익힐 수 있었다. 또한 탐색이 끝난 뒤 방문 상태를 원래대로 되돌리는 백트래킹 과정이 중요하다는 것을 알게 되었다.
