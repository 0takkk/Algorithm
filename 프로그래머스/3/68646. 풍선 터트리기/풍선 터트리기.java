import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        
        int len = a.length;
        int[] left = new int[len];
        int[] right = new int[len];
        
        Arrays.fill(left, Integer.MAX_VALUE);
        Arrays.fill(right, Integer.MAX_VALUE);
        
        left[0] = a[0];
        for(int i = 1; i < len; i++) {
            if(a[i] < left[i-1]) {
                left[i] = a[i];
            } else {
                left[i] = left[i-1];
            }
        }
        
        right[len-1] = a[len-1];
        for(int i = len-2; i >= 0; i--) {
            if(a[i] < right[i+1]) {
                right[i] = a[i];
            } else {
                right[i] = right[i+1];
            }
        }
        
        if(len == 1) {
            answer = 1;
        } else {
            answer = 2;
        }
        
        for(int i = 1; i < len-1; i++) {
            if(!(a[i] > left[i-1] && a[i] > right[i+1])) {
                answer++;
            }
        }
        
        return answer;
    }
}