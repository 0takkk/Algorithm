import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            int n = Integer.parseInt(br.readLine());

            int[] coin = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                coin[i] = Integer.parseInt(st.nextToken());
            }

            int m = Integer.parseInt(br.readLine());

            int[] dp = new int[m+1];
            dp[0] = 1;

            for(int i = 0; i < n; i++){
                for(int j = coin[i]; j <= m; j++){
                    dp[j] += dp[j - coin[i]];
                }
            }

            sb.append(dp[m] + "\n");
        }

        System.out.println(sb.toString());
    }
}
