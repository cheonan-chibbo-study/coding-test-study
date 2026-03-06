class Solution {
    int[] arr;
    int n;
    int[][] memo;

    public int lengthOfLIS(int[] nums) {
        arr = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            arr[i + 1] = nums[i];
        }
        n = arr.length;
        memo = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                memo[i][j] = Integer.MIN_VALUE;
            }
        }

        return recurse(1, 0);
    }

    private int recurse(int i, int last_i) {
        if (i == n) {
            return 0;
        }
        if (memo[i][last_i] != Integer.MIN_VALUE) {
            return memo[i][last_i];
        }

        int result = Integer.MIN_VALUE;
        if ((last_i == 0) || (arr[i] > arr[last_i])) {
            result = Math.max(result, recurse(i + 1, i) + 1);
        }
        result = Math.max(result, recurse(i + 1, last_i));
        memo[i][last_i] = result;
        return result;
    }
}
