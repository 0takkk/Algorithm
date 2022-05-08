import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Integer>[] list;
    public static int[][] dp;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();

        for(int i = 1; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        visited = new boolean[n+1];
        dp = new int[n+1][2];

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void dfs(int idx){
        visited[idx] = true;
        dp[idx][0] = 0;
        dp[idx][1] = 1;

        for(int next : list[idx]){
            if(!visited[next]){
                dfs(next);
                dp[idx][0] += dp[next][1];
                dp[idx][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }

}
