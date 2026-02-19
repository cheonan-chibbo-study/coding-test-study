package week4.CourseSchedule2;
import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        // 그래프 구성 (작성하신 코드와 동일)
        for (int[] pre : prerequisites) {
            int next = pre[0];
            int prev = pre[1];
            adj.get(prev).add(next);
            indegree[next]++;
        }

        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }


        int[] result = new int[numCourses];
        int idx = 0;

        while(!q.isEmpty()){
            int cur = q.poll();

            // 큐에서 꺼낸 과목을 결과 배열에 저장
            result[idx++] = cur;

            for(int next : adj.get(cur)){
                indegree[next]--;
                if(indegree[next] == 0){
                    q.offer(next);
                }
            }
        }


        if (idx == numCourses) {
            return result;
        } else {

            return new int[0];
        }
    }
}