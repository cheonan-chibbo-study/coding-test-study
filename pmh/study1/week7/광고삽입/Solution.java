package study1.week7.광고삽입;
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {

        int playTime = toSeconds(play_time);
        int advTime = toSeconds(adv_time);

        long[] timeLine = new long[playTime + 2];

        for (String log : logs) {
            String[] parts = log.split("-");
            int start = toSeconds(parts[0]);
            int end = toSeconds(parts[1]);

            timeLine[start] += 1;
            timeLine[end] -= 1;
        }

        // 1차 누적합: 각 초의 시청자 수
        for (int i = 1; i <= playTime; i++) {
            timeLine[i] += timeLine[i - 1];
        }

        // 2차 누적합: 0초부터 i초까지의 누적 시청 시간
        for (int i = 1; i <= playTime; i++) {
            timeLine[i] += timeLine[i - 1];
        }

        long maxViewTime = timeLine[advTime - 1];
        int bestStart = 0;

        for (int start = 1; start <= playTime - advTime; start++) {
            int end = start + advTime - 1;
            long currentViewTime = timeLine[end] - timeLine[start - 1];

            if (currentViewTime > maxViewTime) {
                maxViewTime = currentViewTime;
                bestStart = start;
            }
        }

        return toTime(bestStart);
    }

    public String toTime(int seconds) {
        int hour = seconds / 3600;
        seconds %= 3600;
        int minute = seconds / 60;
        int second = seconds % 60;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public static int toSeconds(String time) {
        String[] parts = time.split(":");

        int hour = Integer.parseInt(parts[0]);
        int min = Integer.parseInt(parts[1]);
        int sec = Integer.parseInt(parts[2]);

        return hour * 3600 + min * 60 + sec;
    }
}