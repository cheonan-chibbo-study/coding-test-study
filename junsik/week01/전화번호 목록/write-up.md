문제 링크
https://school.programmers.co.kr/learn/courses/30/lessons/42577

접근방법
1. 한 번호가 다른 번호의 접두사인 경우를 찾아야 하니 각각의 전화번호를 벡터에 저장후 sort()를 이용해 정렬
2. 정렬한 벡터를 substr()함수를 사용하여 겹치는 요소를 검색

다른 풀이
그래도 이 문제는 해쉬 관련 문제이기에 해쉬로 풀 수 있는 방법으로 풀기
#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

bool solution(vector<string> phone_book)
{
    unordered_map<string, int> map;
    for (int i = 0; i < phone_book.size(); i++)
        map[phone_book[i]] = 1;
    for (int i = 0; i < phone_book.size(); i++)
    {
        for (int j = 0; j < phone_book[i].size() - 1; j++)
        {
            string phone_number = phone_book[i].substr(0, j + 1);
            if (map[phone_number])
                return false;
        }
    }
    return true;
}
