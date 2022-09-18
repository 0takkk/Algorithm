import java.io.*;
import java.util.*;

public class Main {

    public static final int DIVISOR = 1000000;
    public static int n;
    public static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        dp = new int[n+1][2][3];  // 일, 지각수, 결석수

        for(int i = 1; i <= n; i++){
            for(int j = 0; j < 2; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(dfs(1, 0, 0));
    }

    public static int dfs(int day, int late, int absent){
        if(late > 1 || absent > 2) return 0;
        if(day > n) return 1;

        if(dp[day][late][absent] != -1) return dp[day][late][absent];

        dp[day][late][absent] = dfs(day+1, late, 0)
                + dfs(day+1, late, absent+1) + dfs(day+1, late+1, 0);

        return dp[day][late][absent] = dp[day][late][absent] >= DIVISOR ? dp[day][late][absent] % DIVISOR : dp[day][late][absent];
    }

}
