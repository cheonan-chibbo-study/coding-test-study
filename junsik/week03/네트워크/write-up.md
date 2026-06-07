# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/43162

# 문제 접근법

1. 아직 방문하지 않은 컴퓨터를 발견하면 DFS를 수행한다.
2. DFS를 통해 현재 컴퓨터와 연결된 모든 컴퓨터를 방문 처리한다.
3. DFS가 한 번 끝날 때마다 하나의 네트워크를 찾은 것이므로 answer를 증가시킨다.
4. 모든 컴퓨터를 탐색한 후 네트워크 개수를 반환한다.

# 소스코드

```cpp
#include <string>
#include <vector>

using namespace std;


void DFS(int cur, vector<bool>& visited, vector<vector<int>>& computers){
    visited[cur] = true;

    for(int next = 0; next < computers.size(); next++)
    {
        if(computers[cur][next] == 1
            && !visited[next])
        {
            DFS(next, visited, computers);
        }
    }
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    vector <bool> visited (n, false);
    
    for(int i = 0; i < n; i++)
    {
        if(!visited[i])
        {
            DFS(i,visited,computers);
            answer++;
        }
    }
    
    return answer;
}
```

# 새로 알게 된 점

* DFS를 수행하면 현재 정점과 연결된 모든 정점을 한 번에 방문할 수 있다.
* 인접 리스트와 인접 행렬의 차이를 알게 되었다.
* DFS 함수를 따로 선언해서 사용하는 법에 대해 알게되었다.