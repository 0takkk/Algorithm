import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n+1];

        dp[0] = 1;
        dp[1] = 0;
        if(n >= 2) dp[2] = 3;

        for(int i = 3; i <= n; i++){
            if(i % 2 == 0){
                int result = 0;
                for(int j = 0; j < i-3; j++){
                    if(j % 2 == 0){
                        result += dp[j] * 2;
                    }
                }
                dp[i] = result + dp[i-2] * 3;
            }
        }

        System.out.println(dp[n]);
    }

}
