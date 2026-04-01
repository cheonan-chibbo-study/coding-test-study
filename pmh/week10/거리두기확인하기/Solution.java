package week10.거리두기확인하기;

import java.util.*;
class Solution {
    int[] br ={-1,0,1, 0};
    int[] bc ={ 0,1,0,-1};
    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for(int i=0;i<5;i++){
            char[][] room = new char[5][5];
            for(int j=0;j<5;j++){
                room[j] = places[i][j].toCharArray();
            }
            boolean result =true;
            for(int r=0;r<5;r++){
                for(int c=0;c<5;c++){

                    if(room[r][c] =='P'){
                        //겹치는사람 x 인경우 true 반환 사람있으면 false
                        if(!bfs(room,r,c)){
                            result =false;

                        }
                    }


                }
            }
            answer[i] = result ? 1:0;
        }
        return answer;
    }
    public boolean bfs(char[][] room,int r,int c){

        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited= new boolean[5][5];
        visited[r][c] = true;
        q.add(new int[]{r,c,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int dist = cur[2];

            // 시작점이 아닌데 P를 만나면 거리두기 실패
            if (!(curR == r && curC == c) && room[curR][curC] == 'P') {
                return false;
            }

            if(dist ==2)continue;
            for(int i=0;i<4;i++){
                int nextR = curR + br[i];
                int nextC= curC+ bc[i];

                if(nextR>-1 && nextR<5 && nextC>-1 && nextC<5 &&
                        !visited[nextR][nextC]
                        &&room[nextR][nextC] != 'X' ){
                    q.add(new int[]{nextR,nextC,dist+1});

                }
            }


        }

        return true;


    }
}