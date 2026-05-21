#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 0;
    queue<pair<int, int>> q;

    for (int i = 0; i < priorities.size(); i++) {
        q.push({i, priorities[i]});
    }

    while (!q.empty()) {
        int index = q.front().first;
        int priority = q.front().second;
        q.pop();

        bool hasHigher = false;

        queue<pair<int, int>> temp = q;

        while (!temp.empty()) {
            if (temp.front().second > priority) {
                hasHigher = true;
                break;
            }
            temp.pop();
        }
        
        if (hasHigher) {
            q.push({index, priority});
        }
        else {
            answer++;

            if (index == location) {
                return answer;
            }
        }
    }

    return answer;
}