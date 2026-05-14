package study1.week13.불량사용자;

import java.util.*;
class Solution {
    /*
    일일히 비교해서
    유저아이디 != 밴 아이디
        차이갯수 ++
        만약 밴아이다기 * 라면
        * 갯수 ++
   차이갯수랑 == * 갯수 같으면
   밴가능아이디
    */
    Set<String> result;
    boolean[] visited;
    public int solution(String[] user_id, String[] banned_id) {
        result = new HashSet<>();
        visited =new boolean[user_id.length];
        dfs(new ArrayList<>(),user_id,banned_id);

        return result.size();
    }
    public void dfs (List<String> cur,String[] user_id,String[] banned_id){
        if(cur.size() == banned_id.length){
            List<String> copy = new ArrayList<>(cur);
            Collections.sort(copy);
            result.add(String.join(",", copy));

            return;
        }
        for(int i=0;i<user_id.length;i++){
            if(!visited[i] && diff(user_id[i],banned_id[cur.size()])){
                visited[i] = true;
                cur.add(user_id[i]);
                dfs(cur,user_id,banned_id);
                visited[i] = false;
                cur.remove(cur.size()-1);

            }


        }

    }
    boolean diff(String user_id,String banned_id){
        int count=0;
        int star =0;
        if(user_id.length() != banned_id.length())return false;
        for(int i=0;i<user_id.length();i++){
            if(user_id.charAt(i) != banned_id.charAt(i) ){
                count++;
                if(banned_id.charAt(i) =='*'){
                    star++;
                }
            }


        }
        if(count == star){
            return true;
        }
        return false;
    }
}