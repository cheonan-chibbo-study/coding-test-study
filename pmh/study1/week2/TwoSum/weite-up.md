# 문제링크
https://leetcode.com/problems/two-sum/submissions/1906143775/
# 접근방법
 1. 처음에는 List 의 contains 를 활용하면 좋을것같다고 생각해 List<Integer> 를 이용해서 문제를 품
2. 그러나 [3,2,4] , 6 입력시 3에서 자기 자신을 참조해서 0,0 을 반환하는 문제점이 생김
3. 또한 list.cotains 와 list.indexof 는 리스트를 처음부터 끝까지 비교하는거라 O(n) 작업이고 이를 반복문안에서 사용하면
   시간복잡도가 O(n^2) 되기때문에 개선할 필요가있었음
4. 그래서 HashMap 을 사용함 숫자를 key,인덱스 value 로 저장해 O(1) 의 속도로 찾을수있게함
