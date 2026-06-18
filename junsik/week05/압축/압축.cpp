#include <string>
#include <vector>
#include <map>

using namespace std;

vector<int> solution(string msg) {

    vector<int> answer;
    map<string, int> dict;

    // A~Z 사전 초기화
    for(int i = 0; i < 26; i++)
    {
        string s;
        s += ('A' + i);

        dict[s] = i + 1;
    }

    int idx = 27;

    for(int i = 0; i < msg.size(); )
    {
        string w;
        w += msg[i];

        int j = i;

        while(j + 1 < msg.size()
              && dict.count(w + msg[j + 1]))
        {
            w += msg[j + 1];
            j++;
        }

        answer.push_back(dict[w]);

        if(j + 1 < msg.size())
        {
            dict[w + msg[j + 1]] = idx++;
        }

        i = j + 1;
    }

    return answer;
}