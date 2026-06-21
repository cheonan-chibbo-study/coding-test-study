#include <string>
#include <vector>

using namespace std;

int solution(int n, int w, int num) {
    int answer = 1;
    int totalRows = (n + w - 1) / w;
    int row = (num - 1) / w;
    int col;

    if(row % 2 == 0)
        col = (num - 1) % w;
    else
        col = w - 1 - ((num - 1) % w);

    for(int r = row + 1; r < totalRows; r++)
    {
        int boxNum;
        
        if(r % 2 == 0)
            boxNum = r * w + col + 1;

        else
            boxNum = r * w + (w - col);

        if(boxNum <= n)
            answer++;
    }

    return answer;
}