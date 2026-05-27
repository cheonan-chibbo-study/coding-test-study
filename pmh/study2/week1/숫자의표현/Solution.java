package study2.week1.숫자의표현;

class Solution {
    public int solution(int n) {
        int sum =0;
        int count=0;
        for(int i=1 ;i<=n;i++){
            for(int j=i;j<=n;j++){
                sum += j;
                if(sum == n){
                    System.out.println(sum);
                    count++;
                    sum=0;
                    break;
                }else if(sum>n){
                    sum=0;
                    break;
                }
            }
        }

        return count;
    }



}
