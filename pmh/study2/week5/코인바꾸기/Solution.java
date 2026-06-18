package study2.week5.코인바꾸기;

class Solution {
    int [] memo;
    public int coinChange(int[] coins, int amount) {
        memo = new int[amount+1];
        Arrays.fill(memo,amount+1);

        memo[0] =0;

        for(int i=1;i<=amount;i++){
            for(int coin : coins){
                if(i-coin>=0){
                    memo[i] = Math.min(memo[i],memo[i-coin] +1);
                }
            }


        }

        return memo[amount] == amount+1 ? -1 : memo[amount];

    }

}