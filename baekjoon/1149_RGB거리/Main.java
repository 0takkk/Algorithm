import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        int ans = 1000000;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(i == j) dp[0][j] = arr[0][j];
                else dp[0][j] = 1000000;
            }

            for(int j = 1; j < n; j++){
                dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + arr[j][0];
                dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + arr[j][1];
                dp[j][2] = Math.min(dp[j-1][0], dp[j-1][1]) + arr[j][2];
            }

            ans = Math.min(ans, Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2])));
        }

        System.out.println(ans);
    }

}
