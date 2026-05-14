# 문제링크

https://leetcode.com/problems/trapping-rain-water/

# 접근방법
1. 특정 위치에 물이 고일려면, 내 왼쪽 과 으론쪽 모두에 나보다 높은 벽이 있어여함
2. 고이는 물의 높이 = min(내 왼쪽에서 가장 높은벽, 내 오른쪽에서 가장 높은벽) - 내 현재 높이
3. 처음에는 현재 인덱스에서 왼쪽 가장 높은벽, 오른쪽 가장 높은벽을 계속 구하는 식으로 했지만 시간초과함 
4. 현재 인덱스에서 왼쪽의 가장 높은벽과 오른쪽의 가장 높은벽의 정보를 미리 저장하는 방식으로 해결함(검색 활용)
```java
  for (int i = 1; i < n; i++) leftMax[i] = Math.max(height[i], leftMax[i - 1]);

    // 오른쪽에서부터 훑으며 각 지점의 max 저장
    rightMax[n - 1] = height[n - 1];
    for (int i = n - 2; i >= 0; i--) rightMax[i] = Math.max(height[i], rightMax[i + 1]);
```
# 배운점 