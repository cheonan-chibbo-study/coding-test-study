package week12.sample;

class Solution {
    /*
 n개 음이 아닌정수
 순서 바꿈 x 적절히 더하거나 뺴서 타켓 넘버 만들기

 4 3 4 1  3
 수가 음수가 되는 조합 dfs 방식
 분기로풀기
 4 3
   -3

 -4 3
    -3
 이런식으로
 */
    int count=0;
    public int solution(int[] numbers, int target) {
        int answer = 0;
        dfs(0,0,numbers,target);
        return count;
    }
    public void dfs(int sum,int index,int[]numbers,int target){
        if(index == numbers.length){
            if(sum == target){
                count++;
            }

            return;
        }

        dfs(sum+numbers[index],index+1,numbers,target);
        dfs(sum-numbers[index],index+1,numbers,target);



    }
}