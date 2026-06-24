#include <string>

using namespace std;

int solution(int n) {
    int answer = 0;
    int count = __builtin_popcount(n);
    n++;
    
    while(true){
        int current = __builtin_popcount(n);
        
        if(count == current){
            answer = n;
            return answer;
        }
        n++;
    }
    return answer;
}