package study2.week1.연속된부분수열의합;

class Solution {
    /*

     */
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;

        int left = 0;
        int sum = 0;

        int bestL = 0;
        int bestR = n - 1;
        int minLen = Integer.MAX_VALUE;
        //sum 이 k 를 초과혐ㄴ left++
        //
        for(int right=0;right<n;right++){
            sum += sequence[right];

            while(sum>k){
                sum-= sequence[left];
                left++;
            }

            if(sum == k){
                int len = right-left;
                if(len < minLen){
                    bestL = left;
                    bestR = right;
                    minLen = len;
                }
            }


        }



        return new int[]{bestL,bestR};
    }
}