import java.util.*;

class Solution {

    List<String> chList = List.of("A", "E", "I", "O", "U");

    public int solution(String word) {
        List<String> words = new ArrayList<>();
        recursive(new StringBuilder(), words);

        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).equals(word)) {
                return i + 1;
            }
        }

        return -1;
    }

    private void recursive(StringBuilder sb, List<String> words) {
        if (sb.length() == 5) {
            return;
        }

        for (String ch : chList) {
            sb.append(ch);
            words.add(sb.toString());
            recursive(sb, words);

            sb.delete(sb.length() - 1, sb.length());
        }
    }
}