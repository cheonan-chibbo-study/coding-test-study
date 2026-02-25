package week5.문자열압축하기;
class Solution {
    public int solution(String s) {
        int n = s.length();
        if (n == 1) return 1;

        int answer = n;

        // 자르는 단위: 1 ~ n/2
        for (int unit = 1; unit <= n / 2; unit++) {
            StringBuilder sb = new StringBuilder();
            String prev = s.substring(0, unit);
            int count = 1;

            // unit씩 이동하면서 비교
            for (int i = unit; i <= n - unit; i += unit) {
                String cur = s.substring(i, i + unit);

                if (cur.equals(prev)) {
                    count++;
                } else {
                    // prev 묶음 처리
                    if (count > 1) sb.append(count);
                    sb.append(prev);

                    prev = cur;
                    count = 1;
                }
            }

            // 마지막 prev 묶음 처리
            if (count > 1) sb.append(count);
            sb.append(prev);

            // 남는 꼬리( unit으로 딱 안 나뉜 부분 ) 붙이기
            int remainStart = (n / unit) * unit; // unit으로 자른 마지막 시작점 다음
            if (remainStart < n) sb.append(s.substring(remainStart));

            answer = Math.min(answer, sb.length());
        }

        return answer;
    }
}