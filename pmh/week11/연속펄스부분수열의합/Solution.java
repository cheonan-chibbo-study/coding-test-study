package week11.연속펄스부분수열의합;

class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;
        int[] arrminus = new int[n];
        int[] arrplus = new int[n];

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                arrminus[i] = -sequence[i];
                arrplus[i] = sequence[i];
            } else {
                arrminus[i] = sequence[i];
                arrplus[i] = -sequence[i];
            }
        }

        long sum1 = kadane( arrminus);
        long sum2 = kadane(arrplus);

        return Math.max(sum1,sum2);
    }
    public long kadane (int[] arr){
        long cur =arr[0];
        long max = arr[0];
        for(int i=1;i<arr.length;i++){
            cur = Math.max(arr[i],arr[i] + cur);
            max = Math.max(max,cur);


        }
        return max;


    }
}