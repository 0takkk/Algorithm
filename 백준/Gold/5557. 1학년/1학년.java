import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        long[] dp = new long[21];
        dp[nums[0]] = 1;

        for(int i = 1; i < n-1; i++) {
            long[] tmp = new long[21];
            for (int j = 0; j <= 20; j++) {
                if (dp[j] != 0) {
                    if(isRange(j - nums[i])) tmp[j-nums[i]] += dp[j];
                    if(isRange(j + nums[i])) tmp[j+nums[i]] += dp[j];
                }
            }
            dp = tmp.clone();
        }

        System.out.println(dp[nums[n-1]]);
    }

    public static boolean isRange(int x) {
        return x >= 0 && x <= 20;
    }

}