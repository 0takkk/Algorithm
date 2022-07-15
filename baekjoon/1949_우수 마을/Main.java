import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] num;
    public static int[][] dp;
    public static boolean[] visited;
    public static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        num = new int[n+1];
        graph = new ArrayList[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            num[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new boolean[n+1];
        dp = new int[3][n+1];

        visited[1] = true;
        dfs(1);

        System.out.println(Math.max(dp[0][1], Math.max(dp[1][1], dp[2][1])));
    }

    public static void dfs(int idx){
        dp[2][idx] = num[idx];

        for(int next : graph[idx]){
            if(!visited[next]){
                visited[next] = true;
                dfs(next);

                dp[0][idx] += Math.max(dp[1][next], dp[2][next]);
                dp[1][idx] += Math.max(dp[0][next], Math.max(dp[1][next], dp[2][next]));
                dp[2][idx] += Math.max(dp[0][next], dp[1][next]);
            }
        }
    }

}
