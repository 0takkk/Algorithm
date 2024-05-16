import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        while(true) {
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            int[] p = new int[n];
            for(int i = 0; i < n; i++) {
                p[i] = Integer.parseInt(br.readLine());
            }

            int[] dp = new int[n];
            dp[0] = p[0];
            int ans = dp[0];
            for(int i = 1; i < n; i++) {
                dp[i] = Math.max(p[i], dp[i-1]+p[i]);
                ans = Math.max(ans, dp[i]);
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

}
