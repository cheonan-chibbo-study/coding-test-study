#include <string>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

bool compare(string a, string b) {
    return a.size() < b.size();
}

vector<int> solution(string s) {

    vector<int> answer;
    vector<string> arr;

    string temp = "";

    for (int i = 0; i < s.size(); i++) {

        if (s[i] == '{') {
            temp = "";
        }

        else if (s[i] == '}') {

            if (!temp.empty()) {
                arr.push_back(temp);
            }
        }

        else {
            temp += s[i];
        }
    }

    sort(arr.begin(), arr.end(), compare);

    set<int> st;

    for (string str : arr) {

        string num = "";

        for (int i = 0; i <= str.size(); i++) {

            if (i == str.size() || str[i] == ',') {

                int n = stoi(num);

                if (st.find(n) == st.end()) {
                    st.insert(n);
                    answer.push_back(n);
                }

                num = "";
            }
            else {
                num += str[i];
            }
        }
    }

    return answer;
}