#include <string>
#include <vector>

using namespace std;

string toBinary(int num)
{
    string binary = "";

    while(num > 0)
    {
        binary = char(num % 2 + '0') + binary;
        num /= 2;
    }

    return binary;
}

vector<int> solution(string s) {
    vector<int> answer;
    int count = 0;
    int zcount = 0;
    
    while (s != "1"){
        int current = 0;
        
        for(char c : s){
            if(c == '0')
                zcount++;
            else
                current++;
        }
        s = toBinary(current);
        count++;
    }
    
    answer.push_back(count);
    answer.push_back(zcount);
    
    return answer;
}