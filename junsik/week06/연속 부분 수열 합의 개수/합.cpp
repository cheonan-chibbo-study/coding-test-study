#include <string>
#include <vector>
#include <set>

using namespace std;

int solution(vector<int> elements) {
    set<int> sums;

    int n = elements.size();

    // 원형 처리를 위해 뒤에 한 번 더 붙이기
    for(int i = 0; i < n; i++)
    {
        elements.push_back(elements[i]);
    }

    // 길이 1 ~ n
    for(int len = 1; len <= n; len++)
    {
        // 시작 위치
        for(int start = 0; start < n; start++)
        {
            int sum = 0;

            for(int i = start; i < start + len; i++)
            {
                sum += elements[i];
            }

            sums.insert(sum);
        }
    }

    return sums.size();
}