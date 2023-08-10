import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        int mod = 1000000009;
        int[][] dp = new int[1001][1001];
        dp[1][1] = 1;
        dp[1][2] = dp[2][2] = 1;
        dp[1][3] = 1;
        dp[2][3] = 2;
        dp[3][3] = 1;

        for(int i = 4; i < 1001; i++) {
            for(int j = 2; j < 1001; j++) {
                for(int k = 1; k <= 3; k++) {
                    if(dp[j-1][i-k] == 0) continue;

                    dp[j][i] = (dp[j][i] + dp[j-1][i-k]) % mod;
                }
            }
        }

        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int ans = 0;
            for(int i = 1; i <= m; i++) {
                ans = (ans + dp[i][n]) % mod;
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

}
