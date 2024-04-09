import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+2];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n+2];
        for(int i = 1; i < n; i++) {
            if(arr[i] > arr[i+1]) dp[i] = dp[i-1]+1;
            else dp[i] = dp[i-1];
        }
        dp[n] = dp[n-1];

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(m-->0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(x == y) {
                sb.append(0).append("\n");
            }
            else {
                sb.append(dp[y-1]-dp[x-1]).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

}