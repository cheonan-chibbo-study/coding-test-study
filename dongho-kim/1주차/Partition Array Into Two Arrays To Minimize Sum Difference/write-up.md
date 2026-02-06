# 문제 링크
https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/

# 접근 방법
Meet in the middle 기법을 사용했다.  
배열을 두 개의 배열로 나눠서, 각 배열 집합에 대해서 부분 수열의 합의 모든 경우를 구한다.

그리고 한 쪽 집합에서 1개부터 n개까지 사용한 모든 경우를 탐색한다.  
그러면 반대쪽에서는 n - (반대쪽에서 사용한 개수) 개수만큼 만들 수 있는 모든 경우의 수가 도출된다.  

이제 한 쪽 집합에서 만들 수 있는 모든 경우의 수가 있을 때, 구해야 하는 수는 `S/2 - L`이 된다.  
이 수를 이용해서 반대쪽 집합에서 이진 탐색으로 `S/2 - L`에 가까운 수를 찾고, 찾은 두 수를 이용해서 차이를 계산한다. 

# 배운 점
1. Meet in the middle
2. 수학적 사고력이 약하다..
