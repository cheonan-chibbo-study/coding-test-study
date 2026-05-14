package study1.week6.MinCostClimbingStairs;

class Solution {
    int[] memo;
    boolean[] visited;
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        memo = new int[n];
        visited = new boolean[n];
        // 꼭대기 도달은 마지막 두 계단 중 최소
        return Math.min(dfs(n - 1, cost), dfs(n - 2, cost));
    }

    public int dfs(int i, int[] cost) {
        // 0번, 1번 계단은 그냥 그 비용
        if (i == 0 || i == 1) {
            return cost[i];
        }
        // cost 가 0 일시 최소비용이 진짜 0이 될수있어서 체크가안되서 메모이제이션이 깨질수있음 -> 방문처리로 변경
        if (visited[i]) return memo[i];

        visited[i] = true;
        memo[i] = cost[i] + Math.min(dfs(i - 1, cost), dfs(i - 2, cost));
        return memo[i];
    }
}