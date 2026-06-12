import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        Set<String> wordSet = new HashSet<>();
        Map<Integer, Set<Character>> graph = new HashMap<>();

        for (String word : words) {
            wordSet.add(word);

            for (int i = 0; i < word.length(); i++) {
                graph.computeIfAbsent(i, key -> new HashSet<>()).add(word.charAt(i));
            }
        }

        // 메인 로직
        if (!wordSet.contains(target)) {
            return 0;
        }

        Deque<Node> dq = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        dq.offer(new Node(begin, 0));
        visited.add(begin);

        while (!dq.isEmpty()) {
            Node cur = dq.poll();

            if (cur.word.equals(target)) {
                return cur.step;
            }

            for (int i = 0; i < cur.word.length(); i++) {
                for (char ch : graph.get(i)) {
                    String left = cur.word.substring(0, i);
                    String right = cur.word.substring(i + 1, cur.word.length());
                    String newWord = left + String.valueOf(ch) + right;

                    if (!wordSet.contains(newWord) || visited.contains(newWord)) {
                        continue;
                    }

                    dq.offer(new Node(newWord, cur.step + 1));
                    visited.add(newWord);
                }
            }
        }

        return 0;
    }

    class Node {
        String word;
        int step;

        public Node(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }
}