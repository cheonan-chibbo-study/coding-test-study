package week2.KeysandRooms;

import java.util.*;

class Solution {
    /*
    n 개의 방이 있고 방들이 0~n-1 호 라벨이 되어있음
    모든 방들음 0 호 룸들으 제외하고 다 잠겨있음
    모든 방을 방문하는게 목표
    그러나 키없이 잠겨진 방을 들어갈수는 없다
    너가 방을 방문할때 방안에서distinct keys 들 을 찾을수있음
    각각 키들은 문의 번호를 가지고있고



    0번 방들어가서 키 가져오기

    가져온 키로 키의 번호에 맞는 방문 열기
    이미 간적 있는 방문이면 넘어가고
    방문한적 없는 방이면 방문열고 키들을 스택에 담기
    방문한 방의 갯수를 +1 하기
    스택이 비어있다면(키로 열수있는 방문을 다열었다면)

    마지막 방문한 방의 갯수와 rooms 의 사이즈가 같으면 true 다르면 false 반환


    */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // 방문 여부 기록
        boolean[] visited = new boolean[rooms.size()];
        // 다음에 방문할 방 번호 담을 스택
        Deque<Integer> stack = new ArrayDeque<>();
        visited[0] = true;
        stack.push(0);


        int visitedCount =1; //방문한 방의 개수 카운트

        while(!stack.isEmpty()){
            int currentKey = stack.pop();

            for(int key : rooms.get(currentKey)){
                //방문 x
                if(!visited[key]){
                    visited[key] = true;
                    stack.push(key);
                    visitedCount++;
                }
            }
        }
        return visitedCount == rooms.size();

    }
}