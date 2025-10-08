import java.util.*;

class Solution {
    
    public static int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (a, b) -> {
            return a[1] - b[1];
        });
        
        int lastEnd = -1;
        
        for(int[] target : targets) {
            int start = target[0];
            int end = target[1];
            
            if(lastEnd <= start) {
                answer++;
                lastEnd = end;
            }
        }
        
        return answer;
    }
}