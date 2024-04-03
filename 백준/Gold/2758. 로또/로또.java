import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        long[][] dp = new long[11][2001];
        for(int i = 1; i <= 2000; i++) {
            dp[1][i] = 1;
        }

        for(int i = 2; i <= 10; i++) {
            for(int j = 1; j <= 2000; j++) {
                if(dp[i-1][j] != 0) {
                    for(int k = j*2; k <= 2000; k++) {
                        dp[i][k] += dp[i-1][j];
                    }
                }
            }
        }

        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            long ans = 0;
            for(int i = 1; i <= m; i++) {
                ans += dp[n][i];
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

}