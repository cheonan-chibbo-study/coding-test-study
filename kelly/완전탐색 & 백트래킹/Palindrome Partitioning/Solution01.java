import java.util.*;

class Solution {

    String s;

    public List<List<String>> partition(String s) {
        this.s = s;

        // 메인 로직
        List<List<String>> answer = new ArrayList<>();
        backTracking(answer, new ArrayList<>(), 0);

        return answer;
    }

    private void backTracking(List<List<String>> result, List<String> temp, int start) {
        if (start == s.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }

        String subStr = "";
        for (int i = start; i < s.length(); i++) {
            subStr += String.valueOf(s.charAt(i));
            if (!isPalindrome(subStr)) {
                continue;
            }

            temp.add(subStr);
            backTracking(result, temp, i + 1);

            temp.remove(temp.size() - 1);
        }
    }

    private boolean isPalindrome(String target) {
        return target.equals(new StringBuilder(target).reverse().toString());
    }
}