import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        int sum = 0;
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        boolean[][] dp = new boolean[sum+1][sum+1];
        dp[0][0] = true;

        for(int i = 0; i < n; i++){
            for(int x = sum; x >= 0; x--){
                for(int y = sum; y >= 0; y--){
                    if(x - arr[i] >= 0){
                        dp[x][y] = dp[x][y] || dp[x - arr[i]][y];
                    }
                    if(y - arr[i] >= 0){
                        dp[x][y] = dp[x][y] || dp[x][y - arr[i]];
                    }
                }
            }
        }

        int ans = 0;
        for(int i = 0; i <= sum; i++){
            for(int j = 0; j <= i; j++){
                int rest = sum - i - j;
                if(dp[i][j] && (j >= rest)){
                    ans = Math.max(ans, rest);
                }
            }
        }

        System.out.println(ans);
    }

}
