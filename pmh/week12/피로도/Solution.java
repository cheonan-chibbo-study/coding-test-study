package week12.피로도;

class Solution {
    /*


     */
    boolean[] visited;
    int maxDun =0;
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];

        dfs(k,dungeons,0);

        return maxDun;
    }
    public void dfs(int k,int[][] dungeons,int count){
        maxDun = Math.max(maxDun,count);
        for(int i=0;i<dungeons.length;i++){
            if(!visited[i] && k>=dungeons[i][0]){
                visited[i] = true;
                dfs(k-dungeons[i][1],dungeons,count+1)    ;
                visited[i] = false;
            }


        }


    }
}