package week6.MaximalSquare;
class Solution {
    /*
    i,j 를 마지막으로하는 정사각형 찾기
    왼쪽 왼쪽위 위에가 1인지검사
     */
    public int maximalSquare(char[][] matrix) {

        int m = matrix.length;
        int n= matrix[0].length;

        int[][] dp = new int[m][n];

        int maxLen =0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){

                if(matrix[i][j] =='1'){
                    if(i==0||j==0){
                        dp[i][j] =1;
                    }else{
                        dp[i][j] = Math.min(dp[i - 1][j],
                                Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                    maxLen = Math.max(maxLen,dp[i][j]);

                }
            }
        }

        return maxLen * maxLen;
    }
}