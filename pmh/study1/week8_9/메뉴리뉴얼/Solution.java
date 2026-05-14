package study1.week8_9.메뉴리뉴얼;

import java.util.*;

class Solution {
    Map<String, Integer> map = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        // 모든 주문에 대해 가능한 조합 생성
        for (String orderStr : orders) {
            char[] order = orderStr.toCharArray();
            Arrays.sort(order); // 같은 조합을 같은 문자열로 만들기 위해 정렬
            dfs(order, 0, new StringBuilder());
        }

        List<String> result = new ArrayList<>();

        // course 길이별로 가장 많이 나온 조합 찾기
        for (int len : course) {
            int maxCount = 0;

            // 1) 해당 길이 조합 중 최대 주문 횟수 찾기
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String menu = entry.getKey();
                int count = entry.getValue();

                if (menu.length() == len && count >= 2) {
                    maxCount = Math.max(maxCount, count);
                }
            }

            // 2) 최대 주문 횟수와 같은 조합들 결과에 추가
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String menu = entry.getKey();
                int count = entry.getValue();

                if (menu.length() == len && count == maxCount && count >= 2) {
                    result.add(menu);
                }
            }
        }

        Collections.sort(result);
        //result.sort();

        return result.toArray(new String[0]);
        //resuslt.toArray(new String[result.size()]);
    }

    public void dfs(char[] order, int start, StringBuilder sb) {
        if (sb.length() >= 2) {
            String key = sb.toString();
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (int i = start; i < order.length; i++) {
            sb.append(order[i]);
            dfs(order, i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}