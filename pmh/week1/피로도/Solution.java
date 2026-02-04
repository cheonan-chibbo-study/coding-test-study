package week1.피로도;
class Solution {
    int maxDun=0;
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        dfs(k,dungeons,visited,0);
        return maxDun;
    }

    public void dfs(int k, int[][]dungeons,boolean[] visited,int count) {


        maxDun = Math.max(maxDun, count);

        for (int i = 0; i < dungeons.length; i++) {
            //방문한적이 없거나  k >=던전[i] 최소 필요도  일때
            if(!visited[i] && k >=dungeons[i][0] ){
                visited[i] =true;
                //k를 소모 피로도 를 뺴서 다시 재귀함수호출
                dfs(k-dungeons[i][1],dungeons,visited,count+1);
                visited[i] =false;
            }

        }
    }
}