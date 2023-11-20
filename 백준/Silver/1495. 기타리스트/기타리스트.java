import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] volume = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            volume[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[n+1][1001];
        dp[0][s] = true;
        
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                if(dp[i-1][j]) {
                    if(j - volume[i] >= 0) dp[i][j-volume[i]] = true;
                    if(j + volume[i] <= m) dp[i][j+volume[i]] = true;
                }
            }
        }

        int ans = -1;
        for(int j = m; j >= 0; j--) {
            if(dp[n][j]) {
                ans = j;
                break;
            }
        }

        System.out.println(ans);
    }

}