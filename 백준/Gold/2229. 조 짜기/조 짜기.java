import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n+1];
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1];
            int max = arr[i];
            int min = arr[i];

            for(int j = i-1; j > 0; j--){
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);

                dp[i] = Math.max(dp[i], dp[j-1]+max-min);
            }
        }

        System.out.println(dp[n]);
    }

}
