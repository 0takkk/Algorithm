import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] memories = new int[n];
        int[] costs = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            costs[i] = Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MAX_VALUE;
        int[][] dp = new int[n][10001];
        for(int i = 0; i < n; i++){
            int memory = memories[i];
            int cost = costs[i];

            for(int j = 0; j <= 10000; j++){
                if(i == 0){
                    if(j >= cost) dp[i][j] = memory;
                }
                else {
                    if (j >= cost) dp[i][j] = Math.max(dp[i - 1][j], dp[i-1][j-cost] + memory);
                    else dp[i][j] = dp[i-1][j];
                }

                if(dp[i][j] >= m) ans = Math.min(ans, j);
            }
        }

        System.out.println(ans);
    }

}
