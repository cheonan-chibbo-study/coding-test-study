package week2.TrappingRainWater;

import java.util.*;
class Solution {

    public int trap(int[] height) {
        int water =0;
        int hesize=height.length;

        for (int i = 1; i < hesize - 1; i++) { // 양 끝은 물이 고일 수 없으므로 제외
            int leftMax = 0;
            int rightMax = 0;

            // 1. 내 왼쪽에서 가장 높은 벽 찾기
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }

            // 2. 내 오른쪽에서 가장 높은 벽 찾기
            for (int j = i; j < hesize; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            // 3. 수위 결정 후 현재 높이 빼기 (이미 자기 자신을 포함해 max를 구했으므로 음수 걱정 없음)
            water += Math.min(leftMax, rightMax) - height[i];
        }
        return water;
    }
}