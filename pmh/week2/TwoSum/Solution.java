package week2.TwoSum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
        public int[] twoSum(int[] nums, int target) {
                //숫자값, 인덱스
            Map<Integer, Integer> number = new HashMap<>();



            int[] result = new int[2];
            for (int i = 0; i < nums.length; i++) {
                int values = target - nums[i];

                if (number.containsKey(values)) {
                    return new int[]{number.get(values), i};
                }
                number.put(nums[i], i);
            }


            throw new IllegalArgumentException("No solution found");
        }

    public static void main(String[] args) {
            Solution sol = new Solution();

        int[] nums = {3, 2, 4};
        int[] result = sol.twoSum(nums, 6);

        System.out.println(result[0] + ", " + result[1]); // 출력: 1, 2
    }
}