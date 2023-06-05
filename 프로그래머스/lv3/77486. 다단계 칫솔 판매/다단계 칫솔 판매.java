import java.util.*;

class Solution {
   
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        HashMap<String, Integer> peopleIdx = new HashMap<>();
        HashMap<String, String> parent = new HashMap<>();
        
        for(int i = 0; i < enroll.length; i++){
            peopleIdx.put(enroll[i], i);
            parent.put(enroll[i], referral[i]);
        }
        
        for(int i = 0; i < seller.length; i++){
            String sell = seller[i];
            int money = amount[i] * 100;
            
            while(parent.containsKey(sell) && money > 0){
                answer[peopleIdx.get(sell)] += (money - (int)(money * 0.1));
                
                sell = parent.get(sell);
                money = (int)(money * 0.1);
            }
        }
        
        return answer;
    }
}