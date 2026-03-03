# 문제 링크
https://leetcode.com/problems/min-cost-climbing-stairs/description/

# 접근 방법
0에서 1에서 모두 시작해보고, 재귀 함수를 통해 1칸 이동하는 경우와 2칸 이동하는 모든 경우의 수를 따져본다.  
이 방법은 O(2^N) 의 시간 복잡도를 가진다.  
따라서 메모이제이션을 이용한 탑다운 DP로 최적화를 수행한다. 탑다운 DP의 시간 복잡도는 O(N) 이다.  

# 배운 점
없습니다.
