package week2.IsGraphBipartite;

import java.util.*;

class Solution {
        /*
        0~n-1 까지 번호가 매겨진 n개의 노드를 가진 무방향 그래프
        2차원 배열 그래프 가 주어지면 grapgh[u] 는 노드 u 와 인저접한 노드들의 배열임
        자가 루프가 없다 : 그래프[u] 는 u를 포함x
        병렬 간선 없음 : 그래프[u] 중복된 값 포함 x
        무방향성 : aksdir v 가 그래프[u] 에 있다면 반드기 u 그래프[v] 에 들어있다
        비연결성 가능 그래프는 하나로연결되어 있지 않을수 있다, 즉 노드 u 와 v 사이에 경로가 존재하지않을수있다
        이분 그래프(Bipartite Graph)의 정의:
        그래프의 노드들을 두 개의 독립된 집합 A와 B로 나누었을 때, 그래프의 모든 간선이 집합 A에 속한 노드와 집합 B에
        속한노드만을 연결한다면 이 그래프를 '이분 그래프'라고 합니다.
        추가 : 그래프의 모든 노드를 두가지 색 (예 : 빨강 파랑) 으로 칠한다고 가정할시 이때 서로 연결된 두노드(인접한 노드)는
        반드시 서로 다른색이여야된다 이렇게 색칠할 수 있다면 그 그래프를 이분 그래프라고한다

        주어진 그래프가 이분 그래프이면 true 를 아니면 false 를 반환하라

        0번 노드 탐색후 1 : 빵갈 칠하기 인접한 노드 정보를 얻고

        방문한 노드인지 확인
        안했다면
        인접한 노드 를 탐색후 에  -1 : 파랑 칠하기 그리고 파랑으로 칠해진 노드에 인접한 노드 정보를얻고
        방문한 노드인지 확인후
        노드 탐색후 방문한 노드에 반대색칠하기
        만약 같은 색을 칠한 노드 발견시 false 반환ㅁ
        무사히 모든 색을 칠한다면 true 반환


         */
        public boolean isBipartite(int[][] graph) {
            int n = graph.length;
            int[] colors = new int[n]; //0 미방문 1: 빨강 -1 파랑

            for(int i =0;i<n;i++){
                //이미 방문한 노드 건너뛰기, 방문 x시에만 탐색시작
                if(colors[i] ==0){
                    Queue<Integer> queue = new LinkedList();
                    queue.offer(i);
                    colors[i] =1;

                    while(!queue.isEmpty()){
                        int curr = queue.poll();
                        for(int neighbor : graph[curr]){
                            //인접한 노드가 같은색이면 이분 그래프x
                            if(colors[neighbor] == colors[curr]){
                                return false;

                            }

                            //아직 색칠 x 노드면 반대 색으로 칠하고 큐에 삽입
                            if(colors[neighbor] == 0){
                                colors[neighbor] = -colors[curr];
                                queue.offer(neighbor);

                            }



                        }


                    }
                }
            }
            return true;
        }
    }