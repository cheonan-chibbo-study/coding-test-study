#include <string>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

int solution(int k, vector<int> tangerine) {
    int answer = 0;
    int sum = 0;
    map<int, int> cnt;

    for (int x : tangerine)
    {
        cnt[x]++;
    }
    
    vector<int> box;

    for(auto item : cnt){
        box.push_back(item.second);
    }

    sort(box.begin(), box.end(), greater<int>());

    for(int x : box){
        sum += x;
        answer++;
        if(sum >= k)
            return answer;
    }
}