import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] height, dp;
    public static HashSet<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dp = new int[n+1];
        height = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            height[i] = Integer.parseInt(st.nextToken());
        }

        graph = new HashSet[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new HashSet<>();
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(height[a] > height[b]) graph[b].add(a);
            else graph[a].add(b);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            sb.append(dfs(i) + "\n");
        }
        System.out.println(sb.toString());
    }

    public static int dfs(int idx){
        if(dp[idx] != 0) return dp[idx];

        dp[idx] = 1;
        for (int next : graph[idx]) {
            dp[idx] = Math.max(dp[idx], dfs(next)+1);
        }

        return dp[idx];
    }

}
