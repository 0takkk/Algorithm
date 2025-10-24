import java.util.*;

class Solution {
    
    public boolean[] alpa;
     Map<String, Integer> map;
    
    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();
        alpa = new boolean[26];
        
        for(int c : course) {
            map = new HashMap<>();
            
            for(String order : orders) {
                if(order.length() < c) {
                    continue;
                }
                
                dfs(0, 0, c, order);
            }
            
            int max = 0;
            for(String key : map.keySet()) {
                max = Math.max(max, map.get(key));
            }
            
            if(max > 1) {
                for(String key : map.keySet()) {
                    if(map.get(key) == max) {
                        result.add(key);
                    }
                }
            }
        }
        
        Collections.sort(result);
        String[] answer = new String[result.size()];
        for(int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    public void dfs(int idx, int cnt, int c, String order) {
        if(cnt == c) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 26; i++) {
                if(alpa[i]) {
                    sb.append((char)('A' + i));
                }                
            }
            
            String pick = sb.toString();
            map.put(pick, map.getOrDefault(pick, 0) + 1);
            
            return;
        }
        
        for(int i = idx; i < order.length(); i++) {
            alpa[order.charAt(i) - 'A'] = true;
            dfs(i+1, cnt+1, c, order);
            alpa[order.charAt(i) - 'A'] = false;
        }
    }
}