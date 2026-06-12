#include <string>
#include <vector>
#include <cmath>

using namespace std;

int answer;
bool used[8];
string line;

char people[8] = {
    'A','C','F','J',
    'M','N','R','T'
};

bool check(vector<string>& data)
{
    int pos[256];

    for(int i = 0; i < 8; i++)
    {
        pos[line[i]] = i;
    }

    for(string s : data)
    {
        char a = s[0];
        char b = s[2];
        char op = s[3];
        int dist = s[4] - '0';

        int gap = abs(pos[a] - pos[b]) - 1;

        if(op == '=' && gap != dist)
            return false;

        if(op == '<' && gap >= dist)
            return false;

        if(op == '>' && gap <= dist)
            return false;
    }

    return true;
}

void DFS(vector<string>& data)
{
    if(line.size() == 8)
    {
        if(check(data))
            answer++;

        return;
    }

    for(int i = 0; i < 8; i++)
    {
        if(used[i])
            continue;

        used[i] = true;
        line.push_back(people[i]);

        DFS(data);

        line.pop_back();
        used[i] = false;
    }
}

int solution(int n, vector<string> data)
{
    answer = 0;
    line = "";

    for(int i = 0; i < 8; i++)
        used[i] = false;

    DFS(data);

    return answer;
}