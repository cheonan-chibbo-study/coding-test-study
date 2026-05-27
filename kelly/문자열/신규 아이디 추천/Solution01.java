import java.util.*;

class Solution {
    public String solution(String new_id) {
        StringBuilder sb;

        // step 01 (문자를 모두 소문자로 변환)
        String step1 = new_id.toLowerCase();

        // step 02 (주어진 문자열에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거한다.)
        Set<Character> allowed = Set.of('-', '_', '.');
        sb = new StringBuilder();

        for (char ch : step1.toCharArray()) {
            if (
                Character.isLowerCase(ch) ||
                    Character.isDigit(ch) ||
                    allowed.contains(ch)
            ) {
                sb.append(ch);
            }
        }

        String step2 = sb.toString();

        // step 03 (주어진 문자열에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환한다.)
        String step3 = step2;

        while (step3.contains("..")) {
            step3 = step3.replace("..", ".");
        }


        // step 04 (주어진 문자열에서 처음과 끝에 위치한 마침표(.)를 제거한다.)
        String step4 = step3;

        while (!step4.isEmpty() && step4.charAt(0) == '.') {
            step4 = step4.substring(1);
        }

        while (!step4.isEmpty() && step4.charAt(step4.length() - 1) == '.') {
            step4 = step4.substring(0, step4.length() - 1);
        }

        // step 05 (주어진 문자열이 빈 문자열이라면, "a"를 대입한다.)
        String step5 = !step4.isEmpty() ? step4 : "a";

        // step 06 (주어진 문자열의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거한다. 만약 제거 후 마침표(.)가 끝에 위치한다면 끝의 마침표(.)를 제거한다.)
        String step6 = step5;
        if (step6.length() > 15) {
            step6 = step6.substring(0, 15);
        }

        while (!step6.isEmpty() && step6.charAt(step6.length() - 1) == '.') {
            step6 = step6.substring(0, step6.length() - 1);
        }

        // step 07 (주어진 문자열의 길이가 2자 이하라면, 마지막 문자를 문자열의 길이가 3이 될 때까지 반복해서 끝에 붙인다.)
        String step7 = step6;

        while (step7.length() <= 2) {
            step7 += step7.charAt(step7.length() - 1);
        }

        return step7;
    }
}