import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        
        int idx = 0;
        for(int i = 1; i <= order.length; i++){
            boolean flag = false;
            
            if(i == order[idx]){
                answer++;
                idx++;
                flag = true;
            }
            
            while(!stack.isEmpty() && order[idx] == stack.peek()){
                stack.pop();
                answer++;
                idx++;
            }
            
            if(!flag) stack.push(i);
        }
        
        return answer;
    }
}