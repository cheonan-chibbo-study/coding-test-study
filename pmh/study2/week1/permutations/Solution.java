package study2.week1.permutations;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited= new boolean[nums.length];
        dfs(result, new ArrayList<>(),visited, nums);
        return result;

    }
    public void dfs(List<List<Integer>> result , List<Integer> cur, boolean[] visited, int[] nums){
        if(cur.size() == nums.length) {
            result.add(new ArrayList(cur));
        }

        for(int i=0;i<nums.length;i++){
            if(!visited[i]){
                visited[i] = true;
                cur.add(nums[i]);
                dfs(result,cur,visited,nums);
                cur.remove(cur.size() -1);
                visited[i] = false;

            }
        }
    }
}