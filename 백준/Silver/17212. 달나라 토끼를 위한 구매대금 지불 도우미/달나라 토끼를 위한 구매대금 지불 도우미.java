import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[100001];
        
        dp[1] = dp[2] = dp[5] = dp[7] = 1;
        dp[3] = dp[4] = dp[6] = 2;

        for(int i = 8; i <= n; i++){
            dp[i] = Math.min(dp[i-1], dp[i-2]) + 1;
            dp[i] = Math.min(dp[i], dp[i-5] + 1);
            dp[i] = Math.min(dp[i], dp[i-7] + 1);
        }

        System.out.println(dp[n]);
    }

}
