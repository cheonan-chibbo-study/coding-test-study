import java.util.*;

class Solution {

    List<String> nums;
    StringBuilder sb;

    public String getPermutation(int n, int k) {
        this.nums = new ArrayList<>();
        for (int num = 1; num <= n; num++) {
            nums.add(String.valueOf(num));
        }

        this.sb = new StringBuilder();

        // 메인 로직
        recursive(k - 1);
        return sb.toString();
    }

    private void recursive(int k) {
        if (nums.size() == 1) {
            sb.append(nums.get(0));
            return;
        }

        int caseCount = facto(nums.size() - 1);
        int target = k / caseCount;
        sb.append(nums.get(target));
        nums.remove(target);

        recursive(k % caseCount);
    }

    private int facto(int num) {
        int result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }

        return result;
    }
}