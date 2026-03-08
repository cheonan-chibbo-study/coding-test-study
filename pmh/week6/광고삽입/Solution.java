package week6.광고삽입;
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        // 1. 전체 재생 시간, 광고 시간 초 단위 변환
        int playSeconds = toSeconds(play_time);
        int advSeconds = toSeconds(adv_time);

        // 초 단위로 시청자 수 변화를 기록할 배열
        // playSeconds + 2 로 넉넉하게 잡아준다
        long[] timeline = new long[playSeconds + 2];

        // 2. 각 로그를 시작/끝 시간으로 나누어서
        // 시작 지점 +1, 끝 지점 -1 기록
        for (String log : logs) {
            String[] parts = log.split("-");
            int start = toSeconds(parts[0]);
            int end = toSeconds(parts[1]);

            timeline[start] += 1;
            timeline[end] -= 1;
        }

        // 3. 첫 번째 누적합
        // timeline[i] = i초에 시청 중인 사람 수
        for (int i = 1; i <= playSeconds; i++) {
            timeline[i] += timeline[i - 1];
        }

        // 4. 두 번째 누적합
        // timeline[i] = 0초부터 i초까지의 누적 시청 시간 합
        for (int i = 1; i <= playSeconds; i++) {
            timeline[i] += timeline[i - 1];
        }

        // 5. 광고를 0초에 넣었을 때의 누적 시청 시간
        long maxViewTime = timeline[advSeconds - 1];
        int bestStart = 0;

        // 6. 광고 시작 시각을 1초부터 끝까지 옮기면서
        // 구간 [start, start + advSeconds - 1] 의 누적 시청 시간 계산
        for (int start = 1; start <= playSeconds - advSeconds; start++) {
            int end = start + advSeconds - 1;

            // 누적합 배열에서 구간합 계산
            long currentViewTime = timeline[end] - timeline[start - 1];

            // 더 큰 값이면 갱신
            // 같을 경우는 "가장 빠른 시작 시각"을 선택해야 하므로
            // strictly greater 일 때만 갱신
            if (currentViewTime > maxViewTime) {
                maxViewTime = currentViewTime;
                bestStart = start;
            }
        }

        // 7. 최적 시작 시간을 다시 HH:MM:SS 형식으로 변환해서 반환
        return toTime(bestStart);
    }

    // "HH:MM:SS" -> 초
    private int toSeconds(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        int second = Integer.parseInt(parts[2]);

        return hour * 3600 + minute * 60 + second;
    }

    // 초 -> "HH:MM:SS"
    private String toTime(int seconds) {
        int hour = seconds / 3600;
        seconds %= 3600;
        int minute = seconds / 60;
        int second = seconds % 60;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}