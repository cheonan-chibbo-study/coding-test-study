package study2.week7.MinimumWindowSubstring;

class Solution {
    public String minWindow(String s, String t) {
        int[] need = new int[128];

        int remain = t.length();
        for(char c : t.toCharArray()){
            need[c]++;
        }


        int left =0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;
        for(int right=0; right<s.length();right++ ){
            char add = s.charAt(right);
            //그 문자가 t 에 포함 되어있다면
            if(need[add] >0){
                //남아있는수 수 줄이기
                remain--;
            }
            //현재 문자 사용
            need[add]--;

            // 모든 문자를 포함한 경우
            while (remain == 0) {
                //길이 갱신
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                char remove = s.charAt(left);

                // 왼쪽 문자 제거
                need[remove]++;

                // 제거한 결과 다시 필요한 문자가 생김
                if (need[remove] > 0) {
                    remain++;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE
                ? ""
                : s.substring(minStart, minStart + minLen);
    }
}