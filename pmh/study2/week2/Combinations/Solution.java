package study2.week2.Combinations;

import java.util.*;
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[n+1];
        dfs(visited,result,new ArrayList<>(),1,n,k);
        return result;
    }
    public void dfs(boolean[] visited,List<List<Integer>> result,List<Integer> cur,int idx,int n,int k){
        if(cur.size() == k){
            result.add(new ArrayList(cur));
            return ;
        }
        for(int i=idx;i<=n;i++){
            if(!visited[i]){
                visited[i] = true;
                cur.add(i);
                dfs(visited,result,cur,i+1,n,k);
                cur.remove(cur.size()-1);
                visited[i] = false;
            }

        }
    }
}