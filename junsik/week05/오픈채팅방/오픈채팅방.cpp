#include <string>
#include <vector>
#include <sstream>
#include <map>

using namespace std;

vector<string> solution(vector<string> record) {
    vector<string> answer;
    
    map<string,string> nickname;
    vector<pair<string,string>> logs;
    
    for(string r : record)
    {
        stringstream ss(r);

        string cmd;
        string uid;
        string name;

        ss >> cmd >> uid;

        if(cmd != "Leave")
            ss >> name;
        
        if(cmd == "Enter" || cmd == "Change")
            nickname[uid] = name;
        
        if(cmd == "Enter" || cmd == "Leave")
            logs.push_back({cmd, uid});
    }
    
    for(auto log : logs)
    {
        string cmd = log.first;
        string uid = log.second;

        if(cmd == "Enter")
            answer.push_back(nickname[uid] + "님이 들어왔습니다.");

        else
            answer.push_back(nickname[uid] + "님이 나갔습니다.");
    }
    
    return answer;
}