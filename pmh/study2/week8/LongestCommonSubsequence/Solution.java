package study2.week8.LongestCommonSubsequence;

class Solution {
    private int[][] memo;
    private String text1;
    private String text2;

    public int longestCommonSubsequence(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;

        memo = new int[text1.length()][text2.length()];

        for (int i = 0; i < text1.length(); i++) {
            Arrays.fill(memo[i], -1);
        }

        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        // 종료 조건
        if (i == text1.length() || j == text2.length()) {
            return 0;
        }

        // 이미 계산한 경우
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // 현재 문자가 같은 경우
        if (text1.charAt(i) == text2.charAt(j)) {
            memo[i][j] = 1 + dfs(i + 1, j + 1);
        }
        // 다른 경우
        else {
            memo[i][j] = Math.max(
                    dfs(i + 1, j),
                    dfs(i, j + 1)
            );
        }

        return memo[i][j];
    }
}