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