package week1.BinarySearch;

public class Solution2 {
    public int search(int[] nums, int target) {
        int start =0;
        int end = nums.length-1;
        while (start <= end) {
            int mid = start+(end-start) /2;

            if (nums[mid] > target) {
                start = mid;
            } else if (nums[mid] < target) {
                end = mid;
            } else {
                return target;
            }
        }

        return -1;

    }
    public static void main(String[]args){
        Solution sol = new Solution();
        int [] nums = {-1,0,3,5,9,12};
        System.out.println(sol.search(nums,9));
    }

}
