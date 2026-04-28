import java.util.*;

class Solution {

    int[] nums;

    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;

        // 메인 로직
        List<List<Integer>> answer = new ArrayList<>();
        recursive(new ArrayList<Integer>(), answer, 0);

        return answer;
    }

    private void recursive(List<Integer> temp, List<List<Integer>> result, int start) {
        result.add(new ArrayList<Integer>(temp));

        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            recursive(temp, result, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}