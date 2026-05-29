import java.util.*;

class Solution {

    String s;

    public int solution(String s) {
        this.s = s;

        // 메인 로직
        if (s.length() == 1) {
            return 1;
        }

        List<Integer> compressed = new ArrayList<>();
        for (int length = 1; length < (s.length() / 2) + 1; length++) {
            compressed.add(compress(length));
        }

        return Collections.min(compressed);
    }

    private int compress(int length) {
        List<String> words = getWords(length);
        StringBuilder sb = new StringBuilder();
        String prevWord = "";
        int count = 1;

        for (String word : words) {
            if (word.equals(prevWord)) {
                count++;
                continue;
            }

            if (count > 1) {
                sb.append(String.valueOf(count));
            }

            sb.append(prevWord);

            prevWord = word;
            count = 1;
        }

        if (count > 1) {
            sb.append(String.valueOf(count));
        }
        sb.append(prevWord);

        return sb.length();
    }

    private List<String> getWords(int length) {
        List<String> words = new ArrayList<>();

        int start = 0;
        while (start + length <= s.length()) {
            words.add(s.substring(start, start + length));
            start += length;
        }

        if (start < s.length()) {
            words.add(s.substring(start, s.length()));
        }

        return words;
    }
}