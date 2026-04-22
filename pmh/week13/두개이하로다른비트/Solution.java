package week13.두개이하로다른비트;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            long x = numbers[i];

            // 짝수면 마지막 비트만 0 -> 1 로 바꾸면 끝
            if ((x & 1L) == 0) {
                answer[i] = x + 1;
            }
            // 홀수면 공식 사용
            else {
                answer[i] = x + 1 + ((x ^ (x + 1)) >> 2);
            }
        }

        return answer;
    }
}