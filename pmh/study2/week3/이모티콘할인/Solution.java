package study2.week3.이모티콘할인;
import java.util.*;

class Solution {
    /*
        서비스 가입자 1
        판매액 2
        n 명 사용자 이모티콘 m 개 할인 판매
        사용자들
         - 자신의 기준 따라 일정 비율 이상 할인하는 이모티콘 모두 구매
         - 이모티콘 구매 비용의 합이 일정 이상일때 이모티콘 구매 취소후 플러스 서비스에 가입
    */

    int maxPlus = 0;
    int maxSales = 0;

    int[] discounts;
    int[] rates = {10, 20, 30, 40};

    public int[] solution(int[][] users, int[] emoticons) {

        // 할인할수있는 모든 경우의수를 구해야함
        discounts = new int[emoticons.length];

        dfs(0, users, emoticons);

        int[] answer = {maxPlus, maxSales};
        return answer;
    }

    public void dfs(int depth, int[][] users, int[] emoticons) {

        if (depth == emoticons.length) {
            cal(users, emoticons);
            return;
        }

        for (int rate : rates) {
            discounts[depth] = rate;
            dfs(depth + 1, users, emoticons);
        }
    }

    public void cal(int[][] users, int[] emoticons) {

        int plusCount = 0;
        int totalSales = 0;

        for (int[] user : users) {

            int minDiscount = user[0];
            int limit = user[1];

            int purchaseAmount = 0;

            for (int i = 0; i < emoticons.length; i++) {

                if (discounts[i] >= minDiscount) {

                    purchaseAmount +=
                            emoticons[i] * (100 - discounts[i]) / 100;
                }
            }

            // 모든 이모티콘 구매금액 계산 후 가입 여부 확인
            if (purchaseAmount >= limit) {
                plusCount++;
            } else {
                totalSales += purchaseAmount;
            }
        }

        // 우선순위 1 : 플러스 가입자 수
        if (plusCount > maxPlus) {
            maxPlus = plusCount;
            maxSales = totalSales;
        }
        // 우선순위 2 : 판매액
        else if (plusCount == maxPlus && totalSales > maxSales) {
            maxSales = totalSales;
        }
    }
}