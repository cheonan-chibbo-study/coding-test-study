package week4.CourseSchedule;
import java.util.*;
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();

        //
        for(int i =0; i< numCourses ; i++){
            adj.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];

        for (int[] pre : prerequisites) {
            int next = pre[0];
            int prev = pre[1];
            adj.get(prev).add(next);
            indegree[next]++;
        }

        Deque<Integer> q = new ArrayDeque<>();
        for(int i=0;i<numCourses;i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }

        int visitedCount =0;
        while(!q.isEmpty()){
            int cur = q.poll();
            visitedCount++;

            for(int next : adj.get(cur)){
                indegree[next]--;
                if(indegree[next] == 0){
                    q.offer(next);
                }
            }
        }
        return visitedCount == numCourses;
    }

}