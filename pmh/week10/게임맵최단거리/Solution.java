package week10.게임맵최단거리;

import java.util.*;
class Solution {
    /*
    1 1 위치

    */
    int[] dx ={-1,0,1,0};
    int[] dy = {0,1,0,-1};
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;

        boolean[][] visited = new boolean[n][m];
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0,0,1});
        visited[0][0] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];
            int dist = cur[2];

            if(cr == n-1 && cc==m-1){
                return dist;
            }
            for(int i=0;i<4;i++){
                int  nr = cr + dx[i];
                int  nc = cc + dy[i];
                if(nr>=0 && nr<n && nc>=0 &&nc<m
                        && maps[nr][nc] == 1
                        && !visited[nr][nc]){
                    visited[nr][nc] = true;
                    q.add(new int[]{nr,nc,dist+1});
                }
            }


        }




        return -1;
    }
}
