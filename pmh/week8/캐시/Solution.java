package week8.캐시;
import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        // 캐시 크기가 0이면, 무조건 miss
        if (cacheSize == 0) {
            return cities.length * 5;
        }

        int answer = 0;
        Deque<String> cache = new ArrayDeque<>();

        for (String city : cities) {
            city = city.toLowerCase(); // 대소문자 구분 안 하므로 소문자로 통일

            // cache hit
            if (cache.contains(city)) {
                cache.remove(city);     // 기존 위치에서 제거
                cache.addLast(city);    // 가장 최근 사용으로 맨 뒤에 삽입
                answer += 1;
            }
            // cache miss
            else {
                if (cache.size() == cacheSize) {
                    cache.removeFirst(); // 가장 오래된 데이터 제거
                }
                cache.addLast(city);     // 새 데이터 삽입
                answer += 5;
            }
        }

        return answer;
    }
}