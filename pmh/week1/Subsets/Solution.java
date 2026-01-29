package week1.Subsets;

import java.util.ArrayList;
import java.util.List;
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result  = new ArrayList<>();


        backtrace(0,nums,new ArrayList<>(),result);

        return result;

    }
    public  void backtrace(int start,int[] nums,List<Integer>current,List<List<Integer>> result){
        //부분집합을 만들면서 생기는 부분들 추가  1,2,3 ,,, 등같은거
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            // 숫자 선택
            current.add(nums[i]);

            backtrace(i+1,nums,current,result);

            current.remove(current.size() - 1);

        }




    }
    public static void  main(String[]args) {
        Solution sol = new Solution();
        int[] nums = {1, 2, 3};
        System.out.println(sol.subsets(nums));

    }



}