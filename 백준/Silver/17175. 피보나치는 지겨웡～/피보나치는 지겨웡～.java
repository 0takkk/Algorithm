import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[n+2];
        dp[n] = 1;
        long ans = 1;

        for(int i = n-1; i >= 1; i--){
            dp[i] = dp[i+1] + dp[i+2];
            ans = (ans + dp[i]) % 1000000007;
        }

        if(n >= 2) ans = (ans + dp[2]) % 1000000007;

        System.out.println(ans);
    }

}
