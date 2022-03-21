import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] meeting = new int[n+1][3];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                meeting[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dp = new int[n+1];
        dp[1] = meeting[1][2];

        int result = 0;

        if(n >= 1) result = dp[1];
        if(n >= 2){
            dp[2] = meeting[2][2];
        }

        for(int i = 3; i <= n; i++){
            dp[i] = meeting[i][2] + result;
            result = Math.max(result, dp[i-1]);
        }

        result = Math.max(result, dp[n]);

        System.out.println(result);
    }

}
