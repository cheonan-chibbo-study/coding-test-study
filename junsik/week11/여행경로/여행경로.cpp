#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<bool> visited;
vector<string> path;
vector<string> answer;
bool finished;

void DFS(string c, vector<vector<string>> &tickets){
    if(path.size() == tickets.size() + 1){
        answer = path;
        finished = true;
        return;
    }
    
    for(int i = 0; i<tickets.size(); i++){
        if(finished){
            return;
        }
        if(visited[i] == false && c == tickets[i][0]){
            visited[i] = true;
            path.push_back(tickets[i][1]);
            DFS(tickets[i][1], tickets);
            visited[i] = false;
            path.pop_back();
        }
    }
}

vector<string> solution(vector<vector<string>> tickets) {
    visited = vector<bool>(tickets.size(), false);
    sort(tickets.begin(), tickets.end());
    path.push_back("ICN");
    
    DFS("ICN", tickets);
    
    return answer;
}