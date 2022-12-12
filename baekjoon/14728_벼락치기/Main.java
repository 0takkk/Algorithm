import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[] dp = new int[t+1];

        while(n-->0){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            for(int i = t; i >= k; i--){
                dp[i] = Math.max(dp[i], dp[i-k] + s);
            }
        }

        System.out.println(dp[t]);
    }

}
