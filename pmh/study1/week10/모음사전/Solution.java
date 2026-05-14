package study1.week10.모음사전;

class Solution {
    char[] words = {'A', 'E', 'I', 'O', 'U'};
    int count = 0;
    int answer =0;
    public int solution(String word) {


        bfs(word,"");
        return answer;
    }
    public void bfs(String word,String traget){
        if(!traget.equals("")){
            count++;
            if(traget.equals(word)){
                answer= count;
                return ;



            }
        }
        if(traget.length() ==5)return;
        for(char c : words){

            bfs(word,traget+c);

        }


    }
}