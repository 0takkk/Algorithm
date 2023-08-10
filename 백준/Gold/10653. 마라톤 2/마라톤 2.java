import java.io.*;
import java.util.*;

public class Main {

    public static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] dist, dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Pos[] pos = new Pos[n+1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pos[i] = new Pos(x, y);
        }

        dist = new int[n+1][n+1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                dist[i][j] = Math.abs(pos[i].x - pos[j].x) + Math.abs(pos[i].y - pos[j].y);
            }
        }

        dp = new int[k+1][n+1];

        System.out.println(check(k, n));
    }

    public static int check(int k, int n) {
        if(dp[k][n] != 0) return dp[k][n];
        if(n == 1) return 0;

        dp[k][n] = Integer.MAX_VALUE;

        for(int i = 0; i <= k; i++) {
            if(n-i-1 > 0) {
                dp[k][n] = Math.min(check(k-i, n-i-1) + dist[n-i-1][n], dp[k][n]);
            }
        }

        return dp[k][n];
    }

}
