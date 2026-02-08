package week2.TrappingRainWater;

//시간초과로인해 개선함
public class Solution2 {
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // 왼쪽에서부터 훑으며 각 지점의 max 저장
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) leftMax[i] = Math.max(height[i], leftMax[i - 1]);

        // 오른쪽에서부터 훑으며 각 지점의 max 저장
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) rightMax[i] = Math.max(height[i], rightMax[i + 1]);

        int water = 0;
        for (int i = 0; i < n; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return water;
    }
}
