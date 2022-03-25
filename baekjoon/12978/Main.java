package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int n;
    public static ArrayList<Integer>[] city;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        city = new ArrayList[n+1];
        for(int i = 0; i <=n; i++)
            city[i] = new ArrayList<>();

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            city[a].add(b);
            city[b].add(a);
        }

        dp = new int[n+1][2];

        dfs(1,0);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void dfs(int cur, int parent){
        for(int next : city[cur]){
            if(next != parent){
                dfs(next, cur);

                dp[cur][0] += dp[next][1];

                dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
        dp[cur][1] += 1;
    }

}
