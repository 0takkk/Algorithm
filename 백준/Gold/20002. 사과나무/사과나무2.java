import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n+1][n+1];
        int[][][] dp = new int[n+1][n+1][n+1];

        int ans = -1000;

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[1][i][j] = arr[i][j];

                ans = Math.max(ans, dp[1][i][j]);
            }
        }

        for(int i = 2; i <= n; i++){
            for(int j = 2; j <= n; j++){
                int maxSize = Math.min(i, j);

                for(int size = 2; size <= maxSize; size++){
                    dp[size][i][j] = arr[i][j] + dp[size-1][i][j-1] + dp[size-1][i-1][j] + arr[i-size+1][j-size+1]-dp[size-2][i-1][j-1];
                    ans = Math.max(ans, dp[size][i][j]);
                }
            }
        }

        System.out.println(ans);
    }
}
