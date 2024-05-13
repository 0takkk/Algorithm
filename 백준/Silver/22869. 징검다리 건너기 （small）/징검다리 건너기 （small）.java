import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] dp = new boolean[n];
        dp[0] = true;

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                int cost = (i-j) * (1 + Math.abs(arr[i] - arr[j]));
                if(cost <= k && dp[j]) {
                    dp[i] = true;
                }
            }
        }

        System.out.println(dp[n-1] ? "YES" : "NO");
    }

}
