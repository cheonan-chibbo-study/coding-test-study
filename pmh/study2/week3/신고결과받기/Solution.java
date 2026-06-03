package study2.week3.신고결과받기;

import java.util.*;
class Solution {
    /*
    각 유저는 한번에 한명 신고가능
        - 신고 횟수 제한 x
        - 한 유저 여러번 신고 가능 그러나 동일한 유저에대한 신고 횟수는 1회
        예) 철수 -> 영이, 영이 이렇게 해도 1회로 처리
    k 번 이상 신고된 유저 게시판 정지 , 유저 신고하 모든 유저에게 정지 사실 메일 발송


    */
    public int[] solution(String[] id_list, String[] report, int k) {

        int n = id_list.length;
        int[] answer = new int[n];
        // index
        Map<String , Integer> idx = new HashMap<>();
        for(int i=0;i<n;i++){
            idx.put(id_list[i],i);
        }


        //신고 받은 사람 -> 신고자  신고자 횟수를 세면 신고 받은 횟수를 얻는게가능
        Map<String, Set<String>> tarToRep = new HashMap<>();
        for(String r: report ){
            String[] cmd = r.split(" ");
            String reporter = cmd[0];
            String target = cmd[1];
            //computeIfAbsent -> 없으면 계산해서 넣어라 key 가 없으면 만들어서 넣고
            // 있으면 기존것 사용해서 넣어라
            /*
            만약 없으면 넣어라 있으면 하지말고
            tarToRep.putIfAbsent(target, new HashSet<>())
            tarToRep.add(reporter)
            */
            tarToRep.computeIfAbsent(target , key -> new HashSet<>()).add(reporter);
        }
        //k 이상 찾아서 (정지대상) 신고자들에게 메일 발송
        for(Map.Entry<String, Set<String>> entry : tarToRep.entrySet()){
            Set<String> reporters = entry.getValue();
            if(reporters.size() >= k){
                for(String reporter : reporters){
                    answer[idx.get(reporter)]++;
                }


            }
        }
        return answer;
    }
}