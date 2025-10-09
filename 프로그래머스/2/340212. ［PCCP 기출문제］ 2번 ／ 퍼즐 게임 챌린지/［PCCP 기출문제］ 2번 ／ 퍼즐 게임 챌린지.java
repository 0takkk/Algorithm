import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = Integer.MAX_VALUE;
        
        int maxLevel = 0;
        for(int diff : diffs) {
            maxLevel = Math.max(maxLevel, diff);
        }
        
        int left = 1;
        int right = maxLevel+1;
        
        while(left < right) {
            int mid = (left + right) / 2;
            
            long totalTime = calculateTotalTime(mid, diffs, times);
            
            if(totalTime <= limit) {
                right = mid;
                answer = Math.min(answer, mid);
            } else {
                left = mid+1;
            }
        }
        
        return answer;
    }
    
    public long calculateTotalTime(int level, int[] diffs, int[] times) {
        long totalTime = times[0];
        
        for(int i = 1; i < diffs.length; i++) {
            int diff = diffs[i];
            int time = times[i];
            
            if(level >= diff) {
                totalTime += time;
            } else {
                totalTime += ((long)(diff-level) * (times[i-1] + time) + time);
            }
        }
        
        return totalTime;
    }
}