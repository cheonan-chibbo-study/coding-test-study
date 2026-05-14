# 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/118667

# 접근방법
1. 하나의 배열로 만들어서
2. q1의 숫자를 미리 다더한후 start =0, end = q1.length-1;
3. 투포인트 사용해서 수가 큐 합의 절반 보다 크면 strat+1 작으면 end+1 하는식으로 푼다
4. 만약 end 나 start 가 두 큐 를 합친 배열의 크기랑 같아지면 0 으로바꿔 다시 탐색하게한다.
