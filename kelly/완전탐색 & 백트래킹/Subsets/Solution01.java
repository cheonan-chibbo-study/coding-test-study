import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        recursive(new ArrayList<Integer>(), answer, nums, 0);
        return answer;
    }

    private void recursive(List<Integer> temp, List<List<Integer>> result, int[] nums, int start) {
        result.add(new ArrayList<Integer>(temp));

        if (temp.size() == nums.length) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            recursive(temp, result, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}