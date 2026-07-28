#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> jobs) {
    sort(jobs.begin(), jobs.end());
    priority_queue<pair<int,int>,vector<pair<int,int>>,
    greater<pair<int,int>>> pq;
    
    int current = 0;
    int index = 0;
    int time = 0;
    
    while(!pq.empty() || index < jobs.size()){
        while(index < jobs.size() && jobs[index][0] <= current){
            pq.push({jobs[index][1], jobs[index][0]});
            index++;
        }
        
        if(!pq.empty()){
            int work = pq.top().first;
            int request = pq.top().second;
            pq.pop();
            
            current += work;
            time += (current - request);
        }
        else
            current++;
    }
    return time / jobs.size();
}