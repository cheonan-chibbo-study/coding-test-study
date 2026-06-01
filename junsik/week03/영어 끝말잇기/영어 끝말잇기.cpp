#include <string>
#include <vector>
#include <set>
#include <iostream>

using namespace std;

vector<int> solution(int n, vector<string> words) {
    vector<int> answer;
    
    //중복된 단어 찾기
    set<string> used;
    used.insert(words[0]);
    
    for(int i = 0; i<words.size() - 1; i++){
        //끝말잇기 규칙 위반 조건
        if(words[i][words[i].size()-1] != words[i+1][0] || 
           used.find(words[i+1]) != used.end()){
            //실패한 지점 저장
            int fail = i+2;
            //실패한 사람 저장
            int person = (fail-1) % n + 1;
            //실패한 사람의 차례
            int turn = (fail-1) / n + 1;
            
            answer.push_back(person);
            answer.push_back(turn);
            
            return answer;
        }
        else{
            used.insert(words[i+1]);
        }
    }
    answer.push_back(0);
    answer.push_back(0);
    
    return answer;
}