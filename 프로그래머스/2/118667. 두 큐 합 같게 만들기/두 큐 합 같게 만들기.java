import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long sum1 = 0, sum2 = 0;
        int len = queue1.length;
        
        for(int q : queue1) {
            sum1 += q;
        }
        
        for(int q : queue2) {
            sum2 += q;
        }
        
        int[] q1 = new int[2*len];
        for(int i = 0; i < len; i++) {
            q1[i] = queue1[i];
            q1[i+len] = queue2[i];
        }
        
        int[] q2 = new int[2*len];
        for(int i = 0; i < len; i++) {
            q2[i] = queue2[i];
            q2[i+len] = queue1[i];
        }
        
        int idx1 = 0, idx2 = 0;
        boolean flag = false;
        
        while(idx1 < 2*len && idx2 < 2*len) {
            if(sum1 == sum2) {
                flag = true;
                break;
            }
            else if(sum1 > sum2) {
                int num = q1[idx1++];
                sum1 -= num;
                sum2 += num;
            }
            else {
                int num = q2[idx2++];
                sum1 += num;
                sum2 -= num;
            }
            answer++;
        }
        
        if(!flag) return -1;
        return answer;
    }
}