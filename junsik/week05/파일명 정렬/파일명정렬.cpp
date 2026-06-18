#include <string>
#include <vector>
#include <algorithm>

using namespace std;

struct File
{
    string head;
    int number;
    string original;
};

bool cmp(File a, File b)
{
    string headA = a.head;
    string headB = b.head;

    for(char& c : headA)
        c = tolower(c);

    for(char& c : headB)
        c = tolower(c);

    if(headA == headB)
        return a.number < b.number;

    return headA < headB;
}

vector<string> solution(vector<string> files)
{
    vector<string> answer;
    vector<File> v;

    for(string file : files)
    {
        string head = "";
        string num = "";

        int idx = 0;

        // HEAD 추출
        while(idx < file.size() &&
              !isdigit(file[idx]))
        {
            head += file[idx];
            idx++;
        }

        // NUMBER 추출 (최대 5자리)
        while(idx < file.size() &&
              isdigit(file[idx]) &&
              num.size() < 5)
        {
            num += file[idx];
            idx++;
        }

        v.push_back({
            head,
            stoi(num),
            file
        });
    }

    stable_sort(v.begin(), v.end(), cmp);

    for(auto file : v)
    {
        answer.push_back(file.original);
    }

    return answer;
}