package study2.week9.메뉴;

import java.util.*;

class Solution {

    Map<String, Integer> map = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        map.clear();

        // 각 주문에서 가능한 메뉴 조합 만들기
        for (String order : orders) {
            char[] orderChar = order.toCharArray();
            Arrays.sort(orderChar);

            dfs(orderChar, 0, new StringBuilder());
        }

        List<String> result = new ArrayList<>();

        // 코스 길이별 가장 많이 주문된 조합 찾기
        for (int len : course) {
            int maxCount = 0;

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String menu = entry.getKey();
                int count = entry.getValue();

                if (menu.length() == len && count >= 2) {
                    maxCount = Math.max(maxCount, count);
                }
            }

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String menu = entry.getKey();
                int count = entry.getValue();

                if (menu.length() == len
                        && count == maxCount
                        && count >= 2) {
                    result.add(menu);
                }
            }
        }

        Collections.sort(result);

        return result.toArray(new String[0]);
    }

    public void dfs(char[] order, int start, StringBuilder sb) {
        // 길이가 2 이상인 조합의 등장 횟수 기록
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