package study2.week11.베스트앨범;

import java.util.*;
class Solution {
    class Song{
        int idx;
        int play;
        Song(int idx,int play){
            this. idx = idx;
            this.play = play;
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        Map<String,Integer> total = new HashMap<>();
        Map<String,List<Song>> play = new HashMap<>();
        for(int i=0;i<genres.length;i++){
            String g =genres[i];
            int p = plays[i];
            total.put(g,total.getOrDefault(g,0)+p);
            play.computeIfAbsent(g, k->new ArrayList<>()).add(new Song(i,p));
        }
        List<String> order = new ArrayList<>(total.keySet());
        Collections.sort(order,(a,b) -> total.get(b) - total.get(a) );
        List<Integer> ans = new ArrayList<>();
        for(String key: order){
            List<Song> list = play.get(key);
            list.sort((a,b) -> {
                if(a.play != b.play)return b.play - a.play;
                return a.idx-b.idx;
            });

            ans.add(list.get(0).idx);
            if(list.size()>1)ans.add(list.get(1).idx);
        }

        int[] answer = new int[ans.size()];
        for(int i=0;i<ans.size();i++){
            answer[i] = ans.get(i);
        }
        return answer;
    }
}