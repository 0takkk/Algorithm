import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        int ans = 1;
        int beforeSeat = 0;
        while(m-->0) {
            int seat = Integer.parseInt(br.readLine());
            ans *= dp[seat-beforeSeat-1];
            beforeSeat = seat;
        }
        ans *= dp[n-beforeSeat];

        System.out.println(ans);
    }

}
