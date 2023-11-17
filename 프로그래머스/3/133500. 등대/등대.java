import java.util.*;

class Solution {
    public static int size;
    public static ArrayList<Integer>[] graph;
    public static int[][] dp;
    public static boolean[] visited;

    public static int solution(int n, int[][] lighthouse) {
        size = n;
        graph = new ArrayList[size+1];
        dp = new int[size+1][2];
        visited = new boolean[size+1];

        for(int i = 1; i <= size; i++) {
            graph[i] = new ArrayList<>();
            dp[i][1] = 1;
        }

        for (int[] light : lighthouse) {
            int from = light[0];
            int to = light[1];

            graph[from].add(to);
            graph[to].add(from);
        }

        visited[1] = true;
        dfs(1);

        return Math.min(dp[1][0], dp[1][1]);
    }

    public static void dfs(int now) {
        for (int next : graph[now]) {
            if(!visited[next]) {
                visited[next] = true;
                dfs(next);
                dp[now][0] += dp[next][1];
                dp[now][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }
}