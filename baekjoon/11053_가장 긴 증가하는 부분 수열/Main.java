import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 0;

        for(int i = 0; i < n; i++){
            int max = 0;
            for(int j = i; j >= 0; j--){
                if(arr[i] > arr[j]){
                    max = Math.max(max, dp[j]);
                }
            }

            dp[i] += max;
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }

}
