import java.util.*;

class Solution {

    int[] nums;

    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;

        // 메인 로직
        List<List<Integer>> answer = new ArrayList<>();
        recursive(new ArrayList<Integer>(), answer);

        return answer;
    }

    private void recursive(List<Integer> temp, List<List<Integer>> result) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (temp.contains(nums[i])) {
                continue;
            }

            temp.add(nums[i]);
            recursive(temp, result);
            temp.remove(temp.size() - 1);
        }
    }
}