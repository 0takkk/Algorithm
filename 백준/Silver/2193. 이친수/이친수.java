import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 1
         * 10
         * 101 100
         * 1000 1001 1010
         * 10000 10001 10100 10101 10010
         *
         */
        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n+1][2];

        dp[1][1] = 1;

        for(int i = 2; i <= n; i++) {
            dp[i][0] = dp[i-1][1] + dp[i-1][0];
            dp[i][1] = dp[i-1][0];
        }

        System.out.println(dp[n][0] + dp[n][1]);
    }

}