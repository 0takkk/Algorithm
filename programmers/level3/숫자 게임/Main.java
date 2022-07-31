import java.util.*;

class Solution {
    
    public int solution(int[] A, int[] B) {
        int ans = 0;
        
        PriorityQueue<Integer> a = new PriorityQueue<>();
        PriorityQueue<Integer> b = new PriorityQueue<>();
        
        for(int i = 0; i < A.length; i++){
            a.offer(A[i]);
            b.offer(B[i]);
        }
        
        while(!b.isEmpty()){
            int n1 = a.peek();
            int n2 = b.peek();
            
            if(n1 < n2){
                ans++;
                a.poll();
                b.poll();
            } else{
                b.poll();
            }
        }
        
        return ans;
    }
    
}
