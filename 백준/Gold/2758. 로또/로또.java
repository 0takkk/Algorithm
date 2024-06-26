import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        long[][] dp = new long[11][2001];
        for(int i = 0; i <= 2000; i++) {
            dp[0][i] = 1;
        }

        for(int i = 1; i <= 10; i++) {
            for(int j = 1; j <= 2000; j++) {
                dp[i][j] = dp[i-1][j/2] + dp[i][j-1];
            }
        }

        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            sb.append(dp[n][m]).append("\n");
        }

        System.out.println(sb.toString());
    }

}