package study2.week4.동전이부족해;

import java.io.*;
import java.util.*;

public class Solution {

    static int N, M;
    static int[] coins;

    static boolean can(int K) {
        int[] dp = new int[M + 1];

        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int coin : coins) {

            for (int sum = 0; sum <= M; sum++) {

                if (dp[sum] >= 0) {
                    dp[sum] = K;
                }
                else if (sum < coin || dp[sum - coin] <= 0) {
                    dp[sum] = -1;
                }
                else {
                    dp[sum] = dp[sum - coin] - 1;
                }
            }
        }

        return dp[M] >= 0;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =
                new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        coins = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }

        if (!can(M)) {
            System.out.println(-1);
            return;
        }

        int left = 0;
        int right = M;
        int answer = -1;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (can(mid)) {
                answer = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}