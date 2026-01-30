package week1.BinarySearch;
import java.util.ArrayList;
import java.util.List;
class Solution {
    public int search(int[] nums, int target) {
        int start =0;
        int end=nums.length-1;
        while (start <=end){
            //(start + end) /2 랑 값이 같지만 오버플로우 방지를 위해서
            int mid = start + (end-start) /2;

            if (nums[mid] < target) {
               start = mid+1;
            }else if (nums[mid] > target){
                end = mid-1;
            }else{
                return mid;
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