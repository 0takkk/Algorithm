import java.util.*;

class Solution {
    public int solution(int[] a) {
        Set<Integer> set = new HashSet<>();
        
        int len = a.length;
        int left = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        
        for(int i = 0; i < len; i++) {
            left = Math.min(left, a[i]);
            right = Math.min(right, a[len-1-i]);
            
            set.add(left);
            set.add(right);
        }
        
        return set.size();
    }
}