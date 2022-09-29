import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n+1];
        Arrays.fill(dp, 987654321);  // 초기화
        dp[1] = 0;

        for(int i = 2; i <= n; i++){
            if(i % 3 == 0) dp[i] = Math.min(dp[i], dp[i/3] + 1);  // 3으로 나뉜다면 dp[i/3]의 연산 횟수 + 1과 최솟값 비교
            if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i/2] + 1);  // 2으로 나뉜다면 dp[i/2]의 연산 횟수 + 1과 최솟값 비교
            dp[i] = Math.min(dp[i], dp[i-1] + 1);  // dp[i-1]의 연산 회수 + 1과 최솟값 비교
        }

        System.out.println(dp[n]);
    }

}
