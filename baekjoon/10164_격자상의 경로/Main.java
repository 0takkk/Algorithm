import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n+1][m+1];
        int ans = 0;

        if(k == 0){
            for(int i = 1; i <= n; i++) dp[i][1] = 1;
            for(int j = 1; j <= m; j++) dp[1][j] = 1;

            for(int i = 2; i <= n; i++){
                for(int j = 2; j <= m; j++){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }

            ans = dp[n][m];
        }
        else{
            int x = (k-1) / m + 1;
            int y = k % m == 0 ? m : k % m;

            for(int i = 1; i <= x; i++) dp[i][1] = 1;
            for(int j = 1; j <= y; j++) dp[1][j] = 1;

            for(int i = 2; i <= x; i++){
                for(int j = 2; j <= y; j++){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }

            for(int i = x+1; i <= n; i++) dp[i][y] = 1;
            for(int j = y+1; j <= m; j++) dp[x][j] = 1;

            for(int i = x+1; i <= n; i++){
                for(int j = y+1; j <= m; j++){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }

            ans = dp[x][y] * dp[n][m];
        }

        System.out.println(ans);
    }

}
