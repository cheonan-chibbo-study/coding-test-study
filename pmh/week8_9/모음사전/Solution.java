package week8_9.모음사전;
class Solution {
    char[] words = {'A', 'E', 'I', 'O', 'U'};
    int count = 0;
    int answer = 0;

    public int solution(String word) {
        dfs("", word);
        return answer;
    }

    public void dfs(String cur, String target) {
        if (!cur.equals("")) {
            count++;

            if (cur.equals(target)) {
                answer = count;
                return;
            }
        }

        if (cur.length() == 5) return;

        for (int i = 0; i < 5; i++) {
            if (answer != 0) return;
            dfs(cur + words[i], target);
        }
    }
}