import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] cost;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        cost = new int[n];
        dp = new int[n][n];

        for(int i = 0; i < n; i++){
            cost[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(cut(0, n-1, 1));
    }

    public static int cut(int start, int end, int cnt){
        if(start > end) return 0;

        if(dp[start][end] != 0) return dp[start][end];

        int front = cut(start+1, end, cnt+1) + cnt * cost[start];
        int back = cut(start, end-1, cnt+1) + cnt * cost[end];

        return dp[start][end] = Math.max(front, back);
    }

}
