# 문제

N개의 마을과 도로 정보가 주어질 때, 1번 마을에서 K 시간 이하로 배달 가능한 마을의 개수를 구하는 문제이다.

# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/12978

# 문제 접근법

1번 마을에서 모든 마을까지의 최단 거리를 구해야 하므로 다익스트라 알고리즘을 사용하였다.

도로 정보를 인접 리스트 형태로 저장한 뒤, 우선순위 큐를 이용하여 가장 짧은 거리부터 탐색하였다.

최종적으로 각 마을까지의 최단 거리가 K 이하인 경우 개수를 세어 정답을 구하였다.

# 소스코드

```cpp
#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(int N, vector<vector<int>> road, int K) {

    vector<vector<pair<int,int>>> graph(N + 1);

    for(auto r : road)
    {
        int a = r[0];
        int b = r[1];
        int cost = r[2];

        graph[a].push_back({b, cost});
        graph[b].push_back({a, cost});
    }

    vector<int> dist(N + 1, 1e9);
    priority_queue<
        pair<int,int>,
        vector<pair<int,int>>,
        greater<pair<int,int>>
    > pq;

    dist[1] = 0;
    pq.push({0, 1});

    while(!pq.empty())
    {
        int cost = pq.top().first;
        int cur = pq.top().second;
        pq.pop();

        if(cost > dist[cur])
            continue;

        for(auto next : graph[cur])
        {
            int nextNode = next.first;
            int nextCost = cost + next.second;

            if(nextCost < dist[nextNode])
            {
                dist[nextNode] = nextCost;
                pq.push({nextCost, nextNode});
            }
        }
    }

    int answer = 0;

    for(int i = 1; i <= N; i++)
    {
        if(dist[i] <= K)
            answer++;
    }

    return answer;
}
```

# 새로 알게 된 점

* 하나의 시작점에서 모든 정점까지의 최단 거리는 다익스트라 알고리즘으로 구할 수 있다.
* 우선순위 큐를 사용하면 가장 짧은 거리부터 효율적으로 탐색할 수 있다.
* 그래프는 인접 리스트로 표현할 수 있다.

# 느낀점

BFS와 비슷해 보였지만 가중치가 존재하여 다익스트라 알고리즘을 사용해야 했다. 최단 거리 문제를 해결하는 대표적인 알고리즘을 익힐 수 있었던 문제였다.
