// 1. 장르의 재생 횟수, -> 2. 장르 내에서 많이 재생된 노래 -> 3. 고유 번호가 낮은 노래

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> playCount = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            playCount.put(genres[i], playCount.getOrDefault(genres[i], 0) + plays[i]);
        }

        PriorityQueue<Category> categoryPq = new PriorityQueue<>((v1, v2) -> Integer.compare(v2.count, v1.count));
        for (String k : playCount.keySet()) {
            categoryPq.offer(new Category(playCount.get(k), k));
        }

        List<Integer> answer = new ArrayList<>();
        while (!categoryPq.isEmpty()) {
            Category curCategory = categoryPq.poll();

            PriorityQueue<Music> musicPq = new PriorityQueue<>((v1, v2) -> {
                if (v1.count != v2.count) return Integer.compare(v2.count, v1.count);
                return Integer.compare(v1.idx, v2.idx);
            });

            for (int idx = 0; idx < plays.length; idx++) {
                if (genres[idx].equals(curCategory.name)) {
                    musicPq.offer(new Music(plays[idx], idx));
                }
            }

            for (int i = 0; i < 2; i++) {
                if (musicPq.isEmpty()) {
                    break;
                }

                Music curMusic = musicPq.poll();
                answer.add(curMusic.idx);
            }
        }

        int[] arrAnswer = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            arrAnswer[i] = answer.get(i);
        }

        return arrAnswer;
    }
}

class Category {

    int count;
    String name;

    public Category(int count, String name) {
        this.count = count;
        this.name = name;
    }
}

class Music {

    int count;
    int idx;

    public Music(int count, int idx) {
        this.count = count;
        this.idx = idx;
    }
}