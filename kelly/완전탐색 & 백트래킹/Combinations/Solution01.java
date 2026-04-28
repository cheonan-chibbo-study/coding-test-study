import java.util.*;

class Solution {

    int n;
    int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;

        // 메인 로직
        List<List<Integer>> answer = new ArrayList<>();
        recursive(new ArrayList<Integer>(), answer, 1);

        return answer;
    }

    private void recursive(List<Integer> temp, List<List<Integer>> result, int start) {
        if (temp.size() == k) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }

        for (int i = start; i <= n; i++) {
            temp.add(i);
            recursive(temp, result, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}