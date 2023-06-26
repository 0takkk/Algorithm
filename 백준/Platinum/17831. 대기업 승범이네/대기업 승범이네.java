import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Integer>[] graph;
    public static int[] weight;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        weight = new int[n+1];
        dp = new int[n+1][2];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 2; i <= n; i++){
            int parent = Integer.parseInt(st.nextToken());
            graph[parent].add(i);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dfs(1, 0));
    }

    public static int dfs(int now, int visited){
        if(dp[now][visited] != 0) return dp[now][visited];

        for (int next : graph[now]) {
            dp[now][visited] += dfs(next, 0);
        }

        if(visited == 0){
            int sum = dp[now][visited];
            for (int next : graph[now]) {
                dp[now][visited] = Math.max(dp[now][visited],
                        sum - dfs(next, 0) + dfs(next, 1) + weight[now] * weight[next]);
            }
        }

        return dp[now][visited];
    }

}
