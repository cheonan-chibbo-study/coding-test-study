package study2.week3.키패드누르기;

import java.util.*;
class Solution {
    /*

    양쪽손 엄지 이용해서 누름
    왼쪽 엄지 * 시작
    오른쪽 엄지 # 시작
    엄지는 4가지방향으로 이동
    1 4 7 왼
    3 6 9 오
    2 5 8 0 가까운 엄지
    거리 같을시 왼손잡이 왼엄지 오른손잡이 온 엄지


    */
    class Hand{
        int pos;
        Hand(int pos){
            this.pos = pos;
        }
    }
    public String solution(int[] numbers, String hand) {
        // 사용 기록
        StringBuilder sb = new StringBuilder();
        int left = 10;
        int right = 12;
        int leftdis = 0;
        int rightdis = 0;
        int idx=0;

        for(int i=0;i<numbers.length;i++){
            if(numbers[i] == 1 ||numbers[i] == 4 ||numbers[i] == 7 ){
                sb.append("L");
                left = numbers[i];
            }
            else if(numbers[i] == 3 ||numbers[i] == 6 ||numbers[i] == 9 ){
                sb.append("R");
                right = numbers[i];
            }else{
                leftdis =  getDistance(left,numbers[i]);
                rightdis =  getDistance(right,numbers[i]);
                if(leftdis > rightdis){
                    sb.append("R");
                    right= numbers[i];
                }else if (leftdis < rightdis){
                    sb.append("L");
                    left = numbers[i];
                }else{// 같을때
                    if(hand.equals("right")){
                        sb.append("R");
                        right= numbers[i];
                    }else{
                        sb.append("L");
                        left = numbers[i];
                    }

                }

            }

        }

        String answer = sb.toString();
        return answer;
    }
    int getDistance(int from, int to) {
        if(from == 0) from = 11;
        if(to == 0) to = 11;

        int r1 = (from - 1) / 3;
        int c1 = (from - 1) % 3;

        int r2 = (to - 1) / 3;
        int c2 = (to - 1) % 3;

        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}