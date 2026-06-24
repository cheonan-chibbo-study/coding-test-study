#include <string>
#include <sstream>
#include <algorithm>

using namespace std;

string solution(string s) {
    string answer = "";
    stringstream ss(s);
    int num;
    
    //첫번째 숫자 읽어오기
    ss>>num;
    
    int maxValue = num;
    int minValue = num;
    
    while(ss >> num){
        maxValue = max(maxValue,num);
        minValue = min(minValue,num);
    }
    
    answer = to_string(minValue) + " " + to_string(maxValue);
    
    return answer;
}