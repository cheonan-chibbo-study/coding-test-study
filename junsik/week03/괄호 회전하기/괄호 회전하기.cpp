#include <string>
#include <vector>
#include <stack>

using namespace std;

bool check(string s) {
    stack<char> test;

    for(char c : s) {

        if(c == '(' || c == '[' || c == '{') {
            test.push(c);
        }
        else {
            if(test.empty())
                return false;

            if(c == ')' && test.top() != '(')
                return false;

            if(c == ']' && test.top() != '[')
                return false;

            if(c == '}' && test.top() != '{')
                return false;

            test.pop();
        }
    }

    return test.empty();
}

int solution(string s) {
    int answer = 0;

    for(int i = 0; i < s.size(); i++) {

        if(check(s))
            answer++;

        char first = s[0];
        s.erase(s.begin());
        s.push_back(first);
    }

    return answer;
}