import java.util.*;

class Solution {
    

    public int size, answer;
    public int[] code;
    public boolean[] selected;
    public int[][] inputs;
    public int[] ANS;
    
    public int solution(int n, int[][] q, int[] ans) {
        
        size = n;
        code = new int[5];
        selected = new boolean[size+1];
        inputs = q;
        ANS = ans;
        
        select(1, 0);
        
        return answer;
    }
    
    public void select(int now, int cnt) {
        if(cnt == 5) {
            if(validate()) {
                answer++;
            }
            return;
        }
        
        for(int i = now; i <= size; i++) {
            if(!selected[i]) {
                selected[i] = true;
                code[cnt] = i;
                select(i+1, cnt+1);
                selected[i] = false;
            }
        }
    }
    
    public boolean validate() {
        for(int i = 0; i < inputs.length; i++) {
            Set<Integer> input = new HashSet<>();
            for(int num : inputs[i]) {
                input.add(num);
            }
            
            int count = 0;
            for(int num : code) {
                if(input.contains(num)) {
                    count++;
                }
            }
            
            if(count != ANS[i]) return false;
        }
        
        return true;
    }
}