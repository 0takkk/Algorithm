import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] run = new int[n+1];
        for(int i = 1; i <= n; i++) {
            run[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[n+1][m+1];
        dp[1][1] = run[1];

        for(int i = 2; i <= n; i++) {
            dp[i][0] = dp[i-1][0];

            int reset = 1;
            for(int j = i-1; j >= Math.max(i-m, 0); j--) {
                dp[i][0] = Math.max(dp[i][0], dp[j][reset++]);
            }

            for(int j = 1; j <= m; j++) {
                dp[i][j] = dp[i-1][j-1] + run[i];
            }
        }

        System.out.println(dp[n][0]);
    }

}