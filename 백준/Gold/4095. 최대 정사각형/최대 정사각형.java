import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            int[][] map = new int[n][m];

            int ans = 0;
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < m; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    ans = Math.max(ans, map[i][j]);
                }
            }

            int[][] dp = new int[n][m];
            for(int i = 0; i < n; i++) dp[i][0] = map[i][0];
            for(int j = 0; j < m; j++) dp[0][j] = map[0][j];
            
            for(int i = 1; i < n; i++){
                for(int j = 1; j < m; j++){
                    if(map[i][j] == 0) continue;

                    int min = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                    dp[i][j] = min+1;

                    ans = Math.max(ans, dp[i][j]);
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

}
