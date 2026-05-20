#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    queue<int> days;
    
    for(int i = 0; i < progresses.size(); i++){
        int start = progresses[i];
        int end = speeds[i];
        int count = 0;
        while(start < 100){
            start += end;
            count++;
        }
        
        days.push(count);
    }
    
    while(!days.empty()){
        int standard = days.front();
        days.pop();
        
        int deployCount = 1;
        
        while(!days.empty() && days.front() <= standard){
            days.pop();
            deployCount++;
        }
        
        answer.push_back(deployCount);
    }
    
    return answer;
}