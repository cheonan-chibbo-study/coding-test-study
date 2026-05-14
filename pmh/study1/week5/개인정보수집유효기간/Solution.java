package study1.week5.개인정보수집유효기간;
import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> map = new HashMap<>();
        // terms map 만들기
        for (String t : terms) {
            String[] split = t.split(" ");
            map.put(split[0], Integer.parseInt(split[1]));
        }

        int todayDate = toDateInt(today);
        List<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            String[] p = privacies[i].split(" ");
            String dateStr = p[0];
            String kind = p[1];

            int startDate = toDateInt(dateStr);
            int months = map.get(kind);

            // 유효기간 끝나는 날짜 계산
            int y = startDate / 10000;
            int m = (startDate / 100) % 100;
            int d = startDate % 100;

            m += months;
            while (m > 12) {
                m -= 12;
                y += 1;
            }

            // 종료일은 하루를 빼야함
            d -= 1;
            if (d == 0) {
                d = 28;
                m -= 1;
                if (m == 0) {
                    m = 12;
                    y -= 1;
                }
            }

            int expireDate = y * 10000 + m * 100 + d;
            if (expireDate < todayDate) {
                answerList.add(i + 1);
            }
        }

        int[] answer = new int[answerList.size()];
        for(int i =0 ; i<answerList.size();i++){
            answer[i]  = answerList.get(i);

        }
        return answer;
    }

    // "YYYY.MM.DD"를 숫자로 변환 (예: 2022.05.19 -> 20220519)
    private int toDateInt(String s) {
        String[] sp = s.split("\\.");
        int y = Integer.parseInt(sp[0]);
        int m = Integer.parseInt(sp[1]);
        int d = Integer.parseInt(sp[2]);
        return y * 10000 + m * 100 + d;
    }
}