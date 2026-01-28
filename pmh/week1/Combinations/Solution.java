package week1.Combinations;
import java.util.ArrayList;
import java.util.List;

class Solution {
    // 가능한 한 모든 조합 찾기 중복 x
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();

        boolean[] visited = new boolean[n+1];

        backtrace(1, new ArrayList<>(), visited, result, n, k);
        return result;
    }

    public void backtrace(int start, List<Integer> current, boolean[] visited, List<List<Integer>> result, int n,
                          int k) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            current.add(i);
            backtrace(i + 1, current, visited, result, n, k);

            visited[i] =false;
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.combine(4, 2));

    }

}