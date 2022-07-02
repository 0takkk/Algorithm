import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;

        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] * 2;

            if(i-3 >= 1 && (i-3) % 2 == 1) dp[i] -= dp[i-4];
            if(i-4 >= 1 && (i-4) % 2 == 0) dp[i] -= dp[i-5];
        }

        System.out.println(dp[n]);
    }

}
