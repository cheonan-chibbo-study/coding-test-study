package week2.CoinChange;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    public int coinChange(int[] coins, int amount) {

        if(amount ==0) return 0;

        Deque<Integer> q = new ArrayDeque<>();

        boolean[] visited = new boolean[amount+1];

        q.add(0);
        visited[0] = true;
        int steps = 0;//동전개수

        while(!q.isEmpty()){
            int size = q.size();

            for(int i=0; i< size; i++){
                int curAmount = q.poll();

                if(curAmount == amount){
                    return steps;
                }

                for(int coin : coins){
                    long nextAmount =(long) curAmount + coin;
                    if(nextAmount <=amount && !visited[(int)nextAmount]){
                        visited[(int)nextAmount] = true;
                        q.add((int)nextAmount);
                    }
                }


            }
            steps++;
        }
        return -1;
    }
}