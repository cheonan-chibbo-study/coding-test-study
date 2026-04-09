package week11.광고삽입;
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        //시간 -> 초 변환
        long playTime =  toSecond(play_time);
        long advTime =  toSecond(adv_time);
        //타임 라인 만들기
        long[] timeLine = new long[(int)playTime+2];

        //타임라인에 log 를 이용해 사람수 채우기 start +=1 end-=1
        for(String log : logs ){
            String[] parts = log.split("-");
            int start = toSecond(parts[0]);
            int end = toSecond(parts[1]);

            timeLine[start] +=1;
            timeLine[end] -=1;

        }

        // 1차 누적합: 각 초의 시청자 수
        for(int time=1;time<=playTime;time++){
            timeLine[time]  = timeLine[time]  + timeLine[time-1];
            //1 0 0 1 0 -1 0 0 00 -1
            //1 1 1 2 2 1  1 1 1 1 0
        }
        // 2차 누적합: 0초부터 i초까지의 누적 시청 시간
        for(int time=1;time<=playTime;time++){
            timeLine[time]  = timeLine[time]  + timeLine[time-1];

            //1 1 1 2 2 1  1 1 1 1 0
            //1 2 3 5 7 8  9 1011 11 11
            //누적 시청시간 수가 다음 사람 시청시간수가 전 시청시간수랑 겹치게되는데 왜 괜찮은             //가?
        }
        //광고를 0초부터  넣은 경우를 기본값으로 먼저 저장하고 start =1 시작
        //광고 60이라면 -> 0~59초까지의 누적시청시긴아 들어가
        // 다음부터는 _ 1~ 부터 시작 이렇게
        long maxTimeView= timeLine[(int)advTime-1];
        int bestStart= 0;
        //구간합을이용해 maxtime 구하기
        for(int start=1 ; start<=playTime-advTime;start++){
            // 왜 -1?-> 광고길이 3초 시작 5초 5 , 6 , 7  3초임 5+3-1 =7
            int end = start + (int)advTime-1;
            long curTimeView = timeLine[end] - timeLine[start-1];

            if (curTimeView > maxTimeView ) {
                maxTimeView =curTimeView;
                bestStart = start;
            }


        }
        //초 -> 시간 바꾸기
        return toTime(bestStart);

    }
    public int toSecond(String time){
        String[] parts = time.split(":");

        int hour = Integer.parseInt(parts[0]);
        int min = Integer.parseInt(parts[1]);
        int second = Integer.parseInt(parts[2]);

        return hour * 3600 + min * 60 + second;


    }
    public String toTime(int time){
        int hour = time /3600;
        time = time %  3600;
        int min = time /60;
        int second = time %  60;
        return String.format("%02d:%02d:%02d",hour,min,second);
    }
}