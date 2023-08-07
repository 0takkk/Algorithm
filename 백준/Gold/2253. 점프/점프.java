import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashSet<Integer> stones = new HashSet<>();
        while(m-->0) {
            stones.add(Integer.parseInt(br.readLine()));
        }

        int max = 100001;
        int[][] dp = new int[n+1][(int)Math.sqrt(2*n)+2];
        for(int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], max);
        }
        dp[1][0] = 0;

        for(int i = 2; i <= n; i++) {
            if(stones.contains(i)) continue;

            for(int j = 1; j < (int) Math.sqrt(2*i)+1; j++) {
                dp[i][j] = Math.min(dp[i-j][j-1], Math.min(dp[i-j][j], dp[i-j][j+1])) + 1;
            }
        }

        int ans = max;
        for(int i = 1; i < (int)Math.sqrt(2*n)+2; i++) {
            ans = Math.min(ans, dp[n][i]);
        }

        if(ans == max) System.out.println(-1);
        else System.out.println(ans);
    }

}
