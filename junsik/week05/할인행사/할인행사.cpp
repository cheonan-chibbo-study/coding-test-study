#include <string>
#include <vector>
#include <map>

using namespace std;

int solution(vector<string> want, vector<int> number, vector<string> discount) {
    int answer = 0;

    map<string, int> wantMap;

    // 원하는 상품 저장
    for(int i = 0; i < want.size(); i++)
    {
        wantMap[want[i]] = number[i];
    }

    // 시작 날짜 이동
    for(int start = 0;
        start <= discount.size() - 10;
        start++)
    {
        map<string, int> discountMap;

        // 10일 동안 할인 상품 개수 세기
        for(int i = start;
            i < start + 10;
            i++)
        {
            discountMap[discount[i]]++;
        }

        bool ok = true;

        // 원하는 상품 비교
        for(int i = 0; i < want.size(); i++)
        {
            if(discountMap[want[i]]
               != wantMap[want[i]])
            {
                ok = false;
                break;
            }
        }

        if(ok)
            answer++;
    }
    return answer;
}