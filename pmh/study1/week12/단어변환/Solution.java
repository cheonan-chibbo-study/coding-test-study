package study1.week12.단어변환;

import java.util.*;
class Solution {
    /*
    begin -> target
    가장 짧은 변환 과정




    */

    class Word {
        String word;
        int count;

        Word(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
    public int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        Queue<Word> q = new LinkedList<>();

        q.offer(new Word(begin, 0));

        while(!q.isEmpty()){
            Word  cur = q.poll();

            if(cur.word.equals(target)){
                return cur.count;
            }

            for(int i=0;i<words.length;i++){
                if(!visited[i] && diffMin(cur.word,words[i])){
                    visited[i] = true;
                    q.offer(new Word(words[i],cur.count+1));
                }



            }
        }




        return 0;
    }
    boolean diffMin(String a, String b){
        int diff=0;
        for(int i=0;i<a.length();i++){
            if(a.charAt(i) != b.charAt(i))diff++;


        }
        return diff ==1;
    }






}