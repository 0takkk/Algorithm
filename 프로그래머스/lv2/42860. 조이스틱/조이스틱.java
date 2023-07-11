import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        int[] alpa = new int[26];
        for(int i = 1; i <= 13; i++){
            alpa[i] = i;
        }
        for(int i = 14; i < 26; i++){
            alpa[i] = 26-i;
        }
        
        int len = name.length();
        int move = len-1;
        
        for(int i = 0; i < len; i++){
            answer += alpa[name.charAt(i) - 'A'];
            
            int idx = i+1;
            while(idx < len && name.charAt(idx) == 'A'){
                idx++;
            }
            
            move = Math.min(move, i * 2 + len-idx);
            move = Math.min(move, (len-idx) * 2 + i);
        }

        return answer + move;
    }
}