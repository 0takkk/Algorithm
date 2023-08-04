import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] dp = new int[c+101];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;

        while(n-->0) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            for(int idx = people; idx < c+101; idx++) {
                dp[idx] = Math.min(dp[idx], cost + dp[idx-people]);
            }
        }

        int ans = 987654321;
        for(int idx = c; idx < c+101; idx++) {
            ans = Math.min(ans, dp[idx]);
        }

        System.out.println(ans);
    }

}
