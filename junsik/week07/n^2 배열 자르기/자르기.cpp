#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(int n, long long left, long long right) {
    vector<int> answer;
    
    for(long long idx = left; idx <= right; idx++){
        long long row = idx / n;
        long long col = idx % n;
        int value = max(row, col) + 1;
        answer.push_back(value);
    }
    return answer;
}