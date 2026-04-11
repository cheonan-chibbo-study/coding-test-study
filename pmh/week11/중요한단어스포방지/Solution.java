package week11.중요한단어스포방지;

import java.util.*;

class Solution {

    static class WordInfo {
        String word;
        int revealStep; // 이 단어가 완전히 공개되는 스포 구간 번호

        WordInfo(String word, int revealStep) {
            this.word = word;
            this.revealStep = revealStep;
        }
    }

    public int solution(String message, int[][] spoiler_ranges) {
        int n = message.length();

        // 각 문자 위치가 어떤 스포 구간에 속하는지 기록
        // 스포가 아니면 -1
        int[] spoilerId = new int[n];
        Arrays.fill(spoilerId, -1);

        for (int i = 0; i < spoiler_ranges.length; i++) {
            int start = spoiler_ranges[i][0];
            int end = spoiler_ranges[i][1];
            for (int p = start; p <= end; p++) {
                spoilerId[p] = i;
            }
        }

        // 평문(스포가 하나도 없는 등장)으로 나온 단어 집합
        Set<String> appearedInPlain = new HashSet<>();

        // 스포 단어 등장들
        List<WordInfo> spoilerWords = new ArrayList<>();

        // message를 단어 단위로 파싱
        int i = 0;
        while (i < n) {
            int start = i;
            while (i < n && message.charAt(i) != ' ') i++;
            int end = i - 1;

            String word = message.substring(start, i);

            boolean hasSpoiler = false;
            int maxSpoilerId = -1;

            for (int p = start; p <= end; p++) {
                if (spoilerId[p] != -1) {
                    hasSpoiler = true;
                    maxSpoilerId = Math.max(maxSpoilerId, spoilerId[p]);
                }
            }

            if (!hasSpoiler) {
                // 이 단어가 평문으로 등장한 적 있음
                appearedInPlain.add(word);
            } else {
                // 이 단어는 maxSpoilerId번째 클릭 때 완전히 공개됨
                spoilerWords.add(new WordInfo(word, maxSpoilerId));
            }

            i++; // 공백 건너뛰기
        }

        // 이전에 공개된 스포 단어들
        Set<String> revealedSpoilerWords = new HashSet<>();

        int answer = 0;
        int idx = 0;

        // 클릭 순서대로 처리
        for (int step = 0; step < spoiler_ranges.length; step++) {
            while (idx < spoilerWords.size() && spoilerWords.get(idx).revealStep == step) {
                String word = spoilerWords.get(idx).word;

                // 중요한 단어 조건:
                // 1) 평문 구간에서 등장한 적 없음
                // 2) 이전에 공개된 스포 단어와 중복 아님
                if (!appearedInPlain.contains(word) && !revealedSpoilerWords.contains(word)) {
                    answer++;
                }

                // 공개된 스포 단어로 등록
                revealedSpoilerWords.add(word);
                idx++;
            }
        }

        return answer;
    }
}