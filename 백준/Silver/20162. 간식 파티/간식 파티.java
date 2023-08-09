import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] foods = new int[n];
        for(int i = 0; i < n; i++) {
            foods[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n];
        dp[0] = foods[0];
        int ans = dp[0];

        for(int i = 1; i < n; i++) {
            dp[i] = foods[i];
            for(int j = i-1; j >= 0; j--) {
                if(foods[i] > foods[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + foods[i]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }

}
