import java.util.*;

class Solution {
    List<List<Integer>> answer = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        for (int size = 0; size <= nums.length; size++) {
            recurse(size, 0, new ArrayList<>(), nums);
        }
        return answer;
    }

    private void recurse(int size, int startIndex, List<Integer> result, int[] nums) {
        if (result.size() == size) {
            answer.add(new ArrayList<>(result));
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (result.size() < size) {
                result.add(nums[i]);
                recurse(size, i+1, result, nums);
                result.remove(result.size() - 1);
            }
        }
    }
}
