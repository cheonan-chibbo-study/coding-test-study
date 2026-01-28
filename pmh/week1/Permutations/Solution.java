package week1.Permutations;

import java.util.*;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        boolean[] visited = new boolean[nums.length];

        backtrace(nums, new ArrayList<>(), visited, result);
        return result;
    }

    public void backtrace(int[] nums, List<Integer> cureent, boolean[] visited, List<List<Integer>> result) {

        if (nums.length == cureent.size()) {
            result.add(new ArrayList<>(cureent));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            cureent.add(nums[i]);

            backtrace(nums, cureent, visited, result);

            visited[i] = false;
            cureent.remove(cureent.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums = { 1, 2, 3 };
        System.out.println(sol.permute(nums));
    }
}