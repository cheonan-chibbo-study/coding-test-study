package week1.구명보트;

import java.util.*;
class Solution {
    /*
    구명보트 최대 2명 무게제한 limit

    구명보트다 최대한 적게 사용해 모든사람구출

    몸무게작은순으로 정렬
    같이 탈 수 있으면 작은 사람도 탔고 큰 사람도 탔으니 둘 다 이동, 못 타면 큰 사람만 혼자 태우고 큰 쪽만 이동하면 되겠네요.






    */
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int left=0;
        int right = people.length-1;
        int sum=0;
        int count=0;

        while(left<=right){
            if(left == right){
                count++;
                break;     }
            sum+= people[left] + people[right];

            //제일큰애만 보내기
            if(sum>limit){
                sum=0;
                count++;
                right--;
            }else if(sum<=limit){ //제일작은애 큰애 같이 보내기
                sum=0;
                count++;
                left++;
                right--;
            }

        }




        return count;
    }
}