package week11.연속된부분의수열의합;

import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;

        int left = 0;
        int sum = 0;

        int bestL = 0;
        int bestR = n - 1;
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += sequence[right];

            while (sum > k) {
                sum -= sequence[left];
                left++;
            }

            if (sum == k) {
                int len = right - left;
                if (len < minLen) {
                    minLen = len;
                    bestL = left;
                    bestR = right;
                }
            }
        }

        return new int[]{bestL, bestR};
    }
}