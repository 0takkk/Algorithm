import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        System.out.println(solution(new int[] {1, 3, 2},
                new String[] {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"}));
        System.out.println(solution(new int[] {0, 1, 1},
                new String[] {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"}));
    }

    public static int ans, max;

    public static int solution(int[] picks, String[] minerals) {
        ans = Integer.MAX_VALUE;
        max = 0;

        for (int pick : picks) {
            max += (pick * 5);
        }
        max = Math.min(max, minerals.length);

        dfs(0, 0, 0, 0, picks, minerals);
        return ans;
    }

    public static void dfs(int idx, int pick, int cnt, int sum, int[] picks, String[] minerals){
        if(ans <= sum) return;

        if(idx == max){
            ans = sum;
            return;
        }

        if(cnt == 0){
            for(int i = 0; i < 3; i++){
                if(picks[i] == 0) continue;

                int tired = getTired(i, minerals[idx]);

                picks[i]--;
                dfs(idx+1, i, cnt+1, sum + tired, picks, minerals);
                picks[i]++;
            }
        }else{
            int tired = getTired(pick, minerals[idx]);
            dfs(idx+1, pick, cnt+1 == 5 ? 0 : cnt+1, sum + tired, picks, minerals);
        }

    }

    public static int getTired(int pick, String mineral){
        if(pick == 0) return 1;
        else if(pick == 1){
            if(mineral.equals("diamond")) return 5;
            else return 1;
        }
        else{
            if(mineral.equals("diamond")) return 25;
            else if(mineral.equals("iron")) return 5;
            else return 1;
        }
    }

}
