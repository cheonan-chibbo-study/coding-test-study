#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(string a, string b){
    return a + b > b + a;
}

string solution(vector<int> numbers) {
    string answer = "";
    vector<string> n;
    
    for(int i = 0; i<numbers.size(); i++){
        n.push_back(to_string(numbers[i]));
    }
    sort(n.begin(), n.end(), compare);
    
    for(int i = 0; i<n.size(); i++){
        answer += n[i];
    }

    // 0이 여러개일 때, 0 하나만 남기기
    if(answer[0] == '0')
        return "0";
    
    return answer;
}