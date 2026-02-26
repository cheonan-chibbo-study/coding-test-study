package week5.신규아이디추천;
import java.util.*;
class Solution {
    public String solution(String new_id) {
        // 1) 소문자
        String id = new_id.toLowerCase();

        // 2) 허용 문자만 남기기 (a-z, 0-9, -, _, .)
        id = id.replaceAll("[^a-z0-9\\-_.]", "");

        // 3) 마침표 2번 이상 -> 1개로
        id = id.replaceAll("\\.{2,}", ".");

        // 4) 처음/끝 마침표 제거
        id = id.replaceAll("^\\.|\\.$", "");

        // 5) 빈 문자열이면 "a"
        if (id.isEmpty()) id = "a";

        // 6) 길이 15로 자르고, 끝 마침표 제거
        if (id.length() >= 16) {
            id = id.substring(0, 15);
            id = id.replaceAll("\\.$", "");
        }

        // 7) 길이 3 될 때까지 마지막 문자 반복
        while (id.length() < 3) {
            id += id.charAt(id.length() - 1);
        }

        return id;
    }
}