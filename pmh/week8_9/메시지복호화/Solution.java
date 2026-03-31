package week8_9.메시지복호화;

class Solution {
    public String solution(String m, String k) {
        StringBuilder answer = new StringBuilder();
        int index = 0;

        for (char c : m.toCharArray()) {
            if (index < k.length() && c == k.charAt(index)) {
                index++;   // 키 문자 하나 제거
            } else {
                answer.append(c); // 원본 메시지에 남김
            }
        }

        return answer.toString();
    }


}
