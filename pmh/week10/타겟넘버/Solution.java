package week10.타겟넘버;
import java.util.*;
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
    int count =0;
    public int solution(int[] numbers, int target) {


        dfs(numbers,target,0,0);
        return count;


    }
    public void dfs(int[] numbers,int target,int start,int sum){
        if(start == numbers.length){
            if(sum == target){
                count++;
            }
            return;
        }

        dfs(numbers,target,start+1,sum - numbers[start]);
        dfs(numbers,target,start+1,sum + numbers[start]);



    }
}