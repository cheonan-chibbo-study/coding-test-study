# 문제링크

https://leetcode.com/problems/permutation-sequence/submissions/1903973022/

# 접근방법
## `itertools` 모듈 사용
1. 주어진 n으로 1부터 n까지의 리스트를 만든다.
2. Python의 `itertools` 모듈의 `permutations` 함수를 사용해서 모든 조합을 구한다.
3. 조합 결과에서 k번 째를 가져온다.

## 모듈 사용 X
1. 1부터 n까지의 숫자 배열을 직접 만들고, 해당 배열에서 오름차순으로 숫자를 하나씩 뽑는 조합을 재귀함수로 구현한다.
2. 모두 뽑으면 종료 조건에서 count를 추가하고, count가 k이면 해당하는 재귀함수의 `result`가 정답이므로 이를 기록한다.
3. 원하는 해를 찾으면 재귀함수에서 `return True`를 반환하여 곧바로 탈출할 수 있도록 구현하여 연산을 최적화했다.

# 배운 점
1. Python은 사기다
