import java.util.*;

class Solution {
    public String solution(int n) {
        int[] arr = new int[]{1, 2, 4};
        
        StringBuilder sb = new StringBuilder();
        
        n = n-1;
        while(n >= 0){
            sb.append(arr[n%3]);
            n /= 3;
            n -= 1;
        } 
        
        return sb.reverse().toString();
    }
}