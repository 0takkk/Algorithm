import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();

        int n = str.length();
        int[] arr = new int[n+1];
        for(int i = 1; i <= n; i++){
            arr[i] = str.charAt(i-1) - '0';
        }

        int[] dp = new int[n+1];

        dp[0] = 1;
        dp[1] = isRange(arr[1]) ? 1 : 0;

        for(int i = 2; i <= n; i++){
            boolean flag1 = isRange(arr[i]);
            boolean flag2 = arr[i - 1] != 0 && isRange(arr[i - 1] * 10 + arr[i]);

            if(!flag1 && !flag2){
                System.out.println(0);
                return;
            }

            if(flag1) {
                dp[i] += dp[i-1];
                dp[i] %= 1000000;
            }
            if(flag2) {
                dp[i] += dp[i-2];
                dp[i] %= 1000000;
            }
        }

        System.out.println(dp[n]);
    }

    public static boolean isRange(int num){
        return num > 0 && num <= 26;
    }

}
