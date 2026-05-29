import java.util.*;

class Solution {

    String s;

    public int solution(String s) {
        this.s = s;

        // 메인 로직
        int answer = s.length();

        for (int length = 1; length < (s.length() / 2) + 1; length++) {
            List<String> words = getWords(length);
            Deque<Item> stack = new ArrayDeque<>();
            stack.push(new Item(words.get(0), 1));

            for (int i = 1; i < words.size(); i++) {
                if (stack.peek().word.equals(words.get(i))) {
                    stack.peek().count++;
                } else {
                    stack.push(new Item(words.get(i), 1));
                }
            }

            StringBuilder sb = new StringBuilder();
            for (Item item : stack) {
                if (item.count > 1) {
                    sb.append(String.valueOf(item.count) + item.word);
                } else {
                    sb.append(item.word);
                }
            }

            answer = Math.min(answer, sb.length());
        }

        return answer;
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

    class Item {
        String word;
        int count;

        public Item(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}