import java.io.*;
import java.util.*;

public class Main {

    public static final int INF = 1000 * 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] homes = new int[n][3];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            homes[i][0] = r;
            homes[i][1] = g;
            homes[i][2] = b;
        }

        int ans = INF;
        int[][] dp = new int[n][3];

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(i == j) dp[0][j] = homes[0][j];
                else dp[0][j] = INF;
            }

            for(int j = 1; j < n; j++){
                dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + homes[j][0];
                dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + homes[j][1];
                dp[j][2] = Math.min(dp[j-1][1], dp[j-1][0]) + homes[j][2];
            }

            for(int j = 0; j < 3; j++){
                if(i != j) ans = Math.min(ans, dp[n-1][j]);
            }
        }

        System.out.println(ans);
    }

}
