#include <string>
#include <vector>
#include <set>
#include <map>

using namespace std;

vector<int> solution(vector<string> id_list, vector<string> report, int k) {
    vector<int> answer;
    set<string> reported; // 중복 제거된 신고 내역
    set<string> banned;   // 정지된 유저
    map<string, int> count; // 유저별 신고당한 횟수
    map<string, int> mail;  // 유저별 메일 수
    
    //중복 제거한 report 만들기
    for(string s : report){
        reported.insert(s);
    }
    
    //신고당한 사람 및 횟수 구하기
    for(string re : reported){
        int cnt = re.find(' ');
        string from = re.substr(0, cnt);
        string to = re.substr(cnt + 1);
        count[to]++;
    }
    
    //정지당한 사람 구하기
    for(string id : id_list){
        if(count[id] >= k){
            banned.insert(id);
        }
    }
    
    //다시 정지당한 사람 탐색하고 해당 메일을 받은 사람의 수 구하기
    for(string r : reported){
        int cnt = r.find(' ');
        string from = r.substr(0, cnt);
        string to = r.substr(cnt + 1);
        
        if(banned.find(to) != banned.end()){
            mail[from]++;
        }
    }
    
    //위에 구한 값들 answer에 저장하기
    for (string id : id_list){
        answer.push_back(mail[id]);
    }
        
    return answer;
}