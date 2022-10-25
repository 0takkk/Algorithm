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

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[] dp = new long[n+1];
        long sum = arr[0];
        int left = 0;
        int right = 1;

        while(right <= n){
            if(sum >= k){
                while(sum >= k) {
                    dp[right] = Math.max(dp[right], dp[left] + sum - k);
                    sum -= arr[left++];
                }
            }else{
                dp[right] = Math.max(dp[right], dp[right-1]);

                if(right == n) break;

                sum += arr[right++];
            }
        }

        System.out.println(dp[n]);
    }

}
