import java.util.*;

class Solution {

    String[] nums;
    Set<Integer> candi;

    public int solution(String numbers) {
        this.nums = new String[numbers.length()];
        for (int i = 0; i < numbers.length(); i++) {
            nums[i] = String.valueOf(numbers.charAt(i));
        }

        this.candi = new HashSet<>();

        // 메인 로직
        boolean[] visited = new boolean[nums.length];
        recursive(new StringBuilder(), visited);

        int answer = 0;
        for (int num : candi) {
            if (isTarget(num)) {
                answer++;
            }
        }

        return answer;
    }

    private void recursive(StringBuilder sb, boolean[] visited) {
        if (sb.length() == nums.length) {
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            sb.append(nums[i]);
            visited[i] = true;
            candi.add(Integer.valueOf(sb.toString()));
            recursive(sb, visited);

            sb.delete(sb.length() - 1, sb.length());
            visited[i] = false;
        }
    }

    private boolean isTarget(int num) {
        if (num == 0 || num == 1) {
            return false;
        }

        if (num < 4) {
            return true;
        }

        for (int i = 2; i <= (int) Math.pow(num, 0.5); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}