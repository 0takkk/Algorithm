import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        int[] sum = new int[n];

        int pSum = 0;
        for(int i = 0; i < m; i++){
            pSum += arr[i];
        }

        sum[m-1] = pSum;

        for(int i = m; i < n; i++){
            pSum = pSum + arr[i] - arr[i-m];
            sum[i] = pSum;
        }

        int[][] dp = new int[n][4];
        dp[m-1][1] = sum[m-1];

        for(int i = m; i < n; i++){
            dp[i][1] = Math.max(dp[i-m][0] + sum[i], dp[i-1][1]);
            dp[i][2] = Math.max(dp[i-m][1] + sum[i], dp[i-1][2]);
            dp[i][3] = Math.max(dp[i-m][2] + sum[i], dp[i-1][3]);
        }

        System.out.println(dp[n-1][3]);
    }

}
