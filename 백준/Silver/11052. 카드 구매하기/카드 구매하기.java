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
        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = arr[i];
        }

        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= (int)Math.ceil((double)(i-1)/2); j++){
                dp[i] = Math.max(dp[i], dp[j] + dp[i-j]);
            }
        }

        System.out.println(dp[n]);
    }
}
