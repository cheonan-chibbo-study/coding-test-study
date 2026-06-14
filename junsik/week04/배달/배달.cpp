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