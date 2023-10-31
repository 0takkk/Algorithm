import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] times = new int[n+1];
        int[] pays = new int[n+1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            pays[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n+2];
        for(int day = n; day > 0; day--) {
            int nextDay = day + times[day];

            if(nextDay > n+1) {
                dp[day] = dp[day+1];
            }
            else {
                dp[day] = Math.max(dp[day+1], dp[nextDay] + pays[day]);
            }
        }

        System.out.println(dp[1]);
    }


}