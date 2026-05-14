package study1.week12.네트워크;

class Solution {
    /*

     */
    int count=0;
    boolean[] visited ;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        for(int i =0;i<n;i++){
            if(!visited[i]){
                dfs(n, i, computers);
                count++;
            }
        }


        return count;
    }
    public void dfs(int n,int index,int[][] computers){
        visited[index] = true;
        for(int i=0;i<n;i++){
            if(!visited[i] && computers[index][i] == 1){

                dfs(n,i,computers);

            }


        }


    }
}