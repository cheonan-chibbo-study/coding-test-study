import java.util.*;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>();
        recursive(new ArrayList<Integer>(), answer, 1, n, k);

        return answer;
    }

    private void recursive(List<Integer> temp, List<List<Integer>> result, int start, int n, int k) {
        if (temp.size() == k) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }

        for (int i = start; i <= n; i++) {
            temp.add(i);
            recursive(temp, result, i + 1, n, k);
            temp.remove(temp.size() - 1);
        }
    }
}