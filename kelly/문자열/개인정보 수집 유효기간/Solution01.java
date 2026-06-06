import java.util.*;

class Solution {

    String today;
    String[] terms;
    String[] privacies;

    Map<String, Integer> tMap;

    public int[] solution(String today, String[] terms, String[] privacies) {
        this.today = today;
        this.terms = terms;
        this.privacies = privacies;

        tMap = new HashMap<>();
        for (String term : terms) {
            String[] t = term.split(" ");
            tMap.put(t[0], Integer.valueOf(t[1]) * 28);
        }

        int todayDay = getDay(today);

        // 메인 로직
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            int endDay = getEndDay(privacies[i]);

            if (todayDay >= endDay) {
                answer.add(i + 1);
            }
        }

        int[] answerArr = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            answerArr[i] = answer.get(i);
        }

        return answerArr;
    }

    private int getEndDay(String privacy) {
        String[] p = privacy.split(" ");
        return getDay(p[0]) + tMap.get(p[1]);
    }

    private int getDay(String date) {
        String[] d = date.split("\\.");
        return (Integer.valueOf(d[0]) * 12 * 28) +
            (Integer.valueOf(d[1]) * 28) +
            Integer.valueOf(d[2]);
    }
}