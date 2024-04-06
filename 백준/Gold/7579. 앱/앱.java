import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] memory = new int[n+1];
        int[] cost = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[10001];
        for(int i = 1; i <= n; i++){
            int mem = memory[i];
            int co = cost[i];

            for(int j = 10000; j >= 0; j--){
                if(j + co >= 10001) continue;

                dp[j + co] = Math.max(dp[j] + mem, dp[j+co]);
            }
        }

        for(int i = 0; i < 10001; i++){
            if(dp[i] >= m){
                System.out.println(i);
                return;
            }
        }
    }

}
