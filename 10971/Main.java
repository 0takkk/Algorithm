package com.company;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int n;
    public static int[][] W;
    public static int min = Integer.MAX_VALUE;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        W = new int[n+1][n+1];
        visited = new boolean[n+1];

        for(int i = 1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " " );
            for(int j = 1; j <= n; j++){
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= n; i++){
            dfs(i, i, 0, 0);
        }

        System.out.println(min);
    }

    public static void dfs(int start, int idx, int cnt, int cost){
        if(cnt == n && start == idx){
            min = Math.min(cost, min);
            return;
        }

        for(int i = 1; i <= n; i++){
            if(W[idx][i] != 0 && !visited[idx]){
                visited[idx] = true;
                cost += W[idx][i];

                dfs(start, i, cnt + 1, cost);

                visited[idx] = false;
                cost -= W[idx][i];
            }
        }
    }

}
