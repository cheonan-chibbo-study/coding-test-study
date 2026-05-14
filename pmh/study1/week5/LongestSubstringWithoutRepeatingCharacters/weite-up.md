# 문제링크
https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/1934149944/
# 30분내 어디까지 풀었는가
 - 15분 정도 풀이를 생각 하고 풀기시작함
 - 문제 푸는 도중에 시간초과
# 접근방법
 - 중복이 되면 안되니 set 을써야겠다고 생각했음 
 - 문자열 길이만큼 배열을 돌리면서 문자열을 문자로 받고 그 문자를 set 에 넣음
 - 그렇게 계속 넣다가 중복 발견시 그 중복 문자열 이 삭제될때까지 left++ 을 해서 0 부터 현재 배열까지 왼쪽부터 지워나감
 - 새로운 문자가 들어올때마다 max 를써서 right-left+1 을 갱신한다
# 배운점 
슬라이딩 윈도우 방식을 알게됨