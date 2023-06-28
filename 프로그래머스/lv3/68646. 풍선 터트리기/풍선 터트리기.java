import java.util.*;

class Solution {
    public static int solution(int[] a) {
        if(a.length == 1) return 1;
        int answer = 2;

        int[] leftMin = new int[a.length];
        int[] rightMin = new int[a.length];

        Arrays.fill(leftMin, Integer.MAX_VALUE);
        Arrays.fill(rightMin, Integer.MAX_VALUE);

        leftMin[0] = a[0];
        rightMin[a.length-1] = a[a.length-1];

        for(int i = 1; i < a.length; i++){
            leftMin[i] = Math.min(leftMin[i-1], a[i]);
        }

        for(int i = a.length-2; i >= 0; i--){
            rightMin[i] = Math.min(rightMin[i+1], a[i]);
        }

        for(int i = 1; i <= a.length-2; i++){
            if(a[i] > leftMin[i] && a[i] > rightMin[i]) continue;
            answer++;
        }

        return answer;
    }
}