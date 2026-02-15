package week1.PalindromePartitioning;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        dfs(0,s,new ArrayList<>(),result);

        return  result;
    }

    public void dfs(int start,String s,List<String>current,List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String sub = s.substring(start, i + 1); //이상~미만 자르기
            if (isPalindrome(sub)) {
                current.add(sub);
                dfs(i+1,s,current,result);
                current.remove(current.size()-1);

            }
        }
    }

    boolean isPalindrome(String sub) {
        int left = 0, right = sub.length() - 1;
        while (left < right) {
            if (sub.charAt(left++) != sub.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}