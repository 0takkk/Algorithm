import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        Map<String, Integer> has = new HashMap<>();
        Set<String> need = new HashSet<>();
        
        for(String gem : gems) {
            has.put(gem, 0);
            need.add(gem); 
        }
        
        int left = 0;
        int right = 0;
        
        has.put(gems[left], 1);
        need.remove(gems[left]);
        int min = Integer.MAX_VALUE;
        
        while(left < gems.length && right < gems.length) {
            if(need.size() == 0) {
                if(min > right - left) {
                    min = right - left;
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                    
                    if(min == has.size()-1) {
                        break;
                    }
                }
                
                has.put(gems[left], has.get(gems[left])-1);
                if(has.get(gems[left]) == 0) {
                    need.add(gems[left]);
                }
                left++;
            } else {
                right++;
                if(right < gems.length) {
                    has.put(gems[right], has.get(gems[right])+1);
                    need.remove(gems[right]);
                }
            }
            
            if(left == right) {
                right++;
                if(right < gems.length) {
                    has.put(gems[right], has.get(gems[right])+1);
                    need.remove(gems[right]);
                }
            }
        }
        
        return answer;
    }
}