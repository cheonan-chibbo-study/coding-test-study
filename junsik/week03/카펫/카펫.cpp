#include <string>
#include <vector>

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> answer;
    int w = 0;
    int h = 0;
    
    int total = brown+yellow;
    
    for(int i = 1; i * i <= total; i++){
        if (total % i == 0) {
            w = total/i;
            h = i;
            
            if((w-2)*(h-2) == yellow){
                answer = {w,h};
            }
        }
    }
    return answer;
}