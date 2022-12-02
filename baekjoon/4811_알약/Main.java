import java.io.*;
import java.util.*;

public class Main {

    public static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        dp = new long[31][31];
        StringBuilder sb = new StringBuilder();

        while(true){
            int n = Integer.parseInt(br.readLine());

            if(n == 0) break;

            sb.append(dfs(n, 0)).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static long dfs(int w, int h){
        if(w == 0) return 1;
        if(dp[w][h] > 0) return dp[w][h];

        // 전체 조각 선택
        dp[w][h] = dfs(w-1, h+1);

        // 반 조각 선택
        if(h > 0) dp[w][h] += dfs(w, h-1);

        return dp[w][h];
    }

}
