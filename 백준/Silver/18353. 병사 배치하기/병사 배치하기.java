import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        int[] dp = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int longCount = 1;
        arr[0] = Integer.MAX_VALUE;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            int max = 0;
            for(int j = i-1; j >= 0; j--) {
                if(arr[i] < arr[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max+1;
            longCount = Math.max(longCount, dp[i]);
        }

        System.out.println(n - longCount);
    }

}