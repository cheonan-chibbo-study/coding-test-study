package study2.week1.subsets;
import java.util.*;
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result,new ArrayList<>(),0,nums);

        return result;
    }
    public void dfs( List<List<Integer>> result, List<Integer> cur,int idx,int[] nums){
        result.add(new ArrayList(cur));

        for(int i=idx;i<nums.length;i++){


            cur.add(nums[i]);
            dfs(result,cur,i+1,nums);
            cur.remove(cur.size()-1);



        }

    }
}