import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 0;

        for(int i = 0; i < n ;i++){
            int max = 0;
            for(int j = i-1; j >= 0; j--){
                if(arr[i] < arr[j]){
                    max = Math.max(dp[j], max);
                }
            }
            dp[i] += max;
            ans = Math.max(dp[i], ans);
        }

        System.out.println(ans);
    }

}
