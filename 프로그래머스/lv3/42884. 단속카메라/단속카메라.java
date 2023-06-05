import java.util.*;

class Solution {
    public static int solution(int[][] routes) {
        int answer = 0;
        int camera = -30000;
        
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        
        for(int[] route : routes){
            if(route[0] <= camera && camera <= route[1]) continue;
            
            camera = route[1];
            answer++;
        }
        
        return answer;
    }
}