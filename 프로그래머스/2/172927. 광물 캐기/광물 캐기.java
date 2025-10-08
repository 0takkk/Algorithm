class Solution {

    public static int answer = Integer.MAX_VALUE;
    
    public static int n, m;
    
    public static int solution(int[] picks, String[] minerals) {
        n = minerals.length;
        
        for(int pick : picks) {
           m += pick; 
        }
        
        System.out.println(n);
        System.out.println(m);
        
        dfs(0, 0, 0, picks, minerals);
        
        return answer;
    }
    
    public static void dfs(int idx, int cnt, int fatigue, int[] picks, String[] minerals) {
        if(idx >= n || cnt >= m) {
            answer = Math.min(answer, fatigue);
            return;
        }
        
        if(fatigue >= answer) {
            return;
        }
        
        for(int i = 0; i < 3; i++) {
            if(picks[i] > 0) {
                picks[i]--;
                dfs(idx+5, cnt+1, fatigue + calcuateFatigue(i, idx, minerals), picks, minerals);
                picks[i]++;
            }
        }
    }
    
    public static int calcuateFatigue(int i, int idx, String[] minerals) {
        int fatigue = 0;
        for(int j = idx; j < idx+5 && j < n; j++) {
            fatigue += getFatigue(i, minerals[j]);
        }
        
        return fatigue;
    }
    
    public static int getFatigue(int pick, String mineral) {
        if(mineral.equals("diamond")) {
            if(pick == 0) {
                return 1;
            } else if(pick == 1) {
                return 5;
            } 
            return 25;
        } else if(mineral.equals("iron")) {
            if(pick == 0) {
                return 1;
            } else if(pick == 1) {
                return 1;
            }
            return 5;
        }
        
        return 1;
    }
}