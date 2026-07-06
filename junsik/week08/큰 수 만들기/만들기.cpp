#include <string>
#include <stack>
#include <algorithm>

using namespace std;

string solution(string number, int k) {
    string answer = "";
    stack<char> st;

    for(char c : number)
    {
        while(!st.empty() && k > 0 && st.top() < c)
        {
            st.pop();
            k--;
        }

        st.push(c);
    }
    while(k > 0)
    {
        st.pop();
        k--;
    }
    while(!st.empty())
    {
        answer += st.top();
        st.pop();
    }

    reverse(answer.begin(), answer.end());
    return answer;
}