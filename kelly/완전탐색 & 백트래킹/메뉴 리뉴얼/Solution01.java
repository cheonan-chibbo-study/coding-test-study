import java.util.*;

class Solution {

    String[] orders;
    int[] course;

    List<List<String>> menus = new ArrayList<>();

    public String[] solution(String[] orders, int[] course) {
        this.orders = orders;
        this.course = course;
        for (String order : orders) {
            String[] menuArr = order.split("");
            List<String> menuItem = new ArrayList<>();
            for (int i = 0; i < order.length(); i++) {
                menuItem.add(menuArr[i]);
            }

            Collections.sort(menuItem);
            menus.add(menuItem);
        }

        // 메인 로직
        List<String> answer = new ArrayList<>();

        for (int cnt : course) {
            List<String> maximumOrder = getMaximumOrder(cnt);
            for (String o : maximumOrder) {
                answer.add(o);
            }
        }

        Collections.sort(answer);

        String[] arrAnswer = new String[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            arrAnswer[i] = answer.get(i);
        }

        return arrAnswer;
    }

    private List<String> getMaximumOrder(int cnt) {
        Map<String, Integer> orderCount = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (List<String> menu : menus) {
            getCombi(menu, orderCount, sb, cnt, 0);
        }

        int maximum = -1;
        List<String> result = new ArrayList<>();
        for (String c : orderCount.keySet()) {
            int orderCnt = orderCount.get(c);

            if (orderCnt >= 2) {
                if (orderCnt > maximum) {
                    result = new ArrayList<>();
                    result.add(c);
                    maximum = orderCnt;
                } else if (orderCnt == maximum) {
                    result.add(c);
                }
            }
        }

        return result;
    }

    private void getCombi(
        List<String> menu,
        Map<String, Integer> orderCount,
        StringBuilder sb,
        int cnt,
        int start
    ) {
        if (sb.length() == cnt) {
            String s = sb.toString();
            orderCount.put(s, orderCount.getOrDefault(s, 0) + 1);
            return;
        }

        for (int i = start; i < menu.size(); i++) {
            sb.append(menu.get(i));
            getCombi(menu, orderCount, sb, cnt, i + 1);
            sb.setLength(sb.length() - 1);
        }
    }
}