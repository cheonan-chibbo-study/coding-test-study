package week1.PermutationSequence;

import java.util.ArrayList;
import java.util.List;
class Solution {
    public String getPermutation(int n, int k) {
        boolean[] visited = new boolean[n + 1];
        List<List<Integer>> result = new ArrayList<>();
        dfs(n,result,new ArrayList<>(),visited);

        // 숫자를 합치기 위한 StringBuilder 사용
        StringBuilder sb = new StringBuilder();
        for (int num : result.get(k - 1)) {
            sb.append(num);
        }
        return sb.toString();
    }

    public void dfs(int n, List<List<Integer>> result, List<Integer> current,boolean[] visited) {
        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            current.add(i);
            dfs(n,result,current,visited);

            visited[i]=false;
            current.remove(current.size() - 1);


        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.getPermutation(3,3));
    }
}