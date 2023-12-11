import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];

        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[n+1][3];
        dp[1][0] = 0;
        dp[1][1] = arr[1];
        if(n >= 2) {
            dp[2][0] = arr[1];
            dp[2][1] = arr[2];
            dp[2][2] = arr[1] + arr[2];
        }


        for(int i = 3; i <= n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
            dp[i][1] = dp[i-1][0] + arr[i];
            dp[i][2] = dp[i-1][1] + arr[i];
        }

        System.out.println(Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2])));
    }

}