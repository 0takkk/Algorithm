import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        HashMap<String, Integer> wantedMap = new HashMap<>();
        for(int i = 0; i < want.length; i++){
            wantedMap.put(want[i], number[i]);
        }
        
        int len = discount.length - 9; 
        
        HashMap<String, Integer> canBuy = new HashMap<>();
        for(int i = 0; i <= 9; i++){
            canBuy.put(discount[i], canBuy.getOrDefault(discount[i], 0) + 1);
        }
        
        if(isCanBuy(wantedMap, canBuy)) answer++;
        
        for(int i = 1; i < len; i++){
            String prevFood = discount[i-1];
            String nextFood = discount[i+9];
            
            canBuy.put(prevFood, canBuy.get(prevFood)-1);
            canBuy.put(nextFood, canBuy.getOrDefault(nextFood, 0) + 1);
            
            if(isCanBuy(wantedMap, canBuy)) answer++;
        }
        
        return answer;
    }
    
    public static boolean isCanBuy(HashMap<String, Integer> wantedMap, HashMap<String, Integer> canBuy){
        for(String key : wantedMap.keySet()){
            int wantedCount = wantedMap.get(key);
            
            if(!canBuy.containsKey(key) || canBuy.get(key) < wantedCount) return false;
        }
        
        return true;
    }
}