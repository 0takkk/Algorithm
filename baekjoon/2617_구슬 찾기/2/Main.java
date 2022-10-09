import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Integer>[] graph;
    public static int[][] dp;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
        }

        dp = new int[n+1][2];

        for(int i = 1; i <= n; i++){
            visited = new boolean[n+1];
            dfs(i, i);
        }

        int ans = 0;
        for(int i = 1; i <= n; i++){
            if(dp[i][0] >= (n/2+1) || dp[i][1] >= (n/2+1)) ans++;
        }

        System.out.println(ans);
    }

    public static void dfs(int now, int start){
        visited[now] = true;

        for(int next : graph[now]){
            if(!visited[next]){
                dp[start][0]++;
                dp[next][1]++;
                dfs(next, start);
            }
        }
    }

}
