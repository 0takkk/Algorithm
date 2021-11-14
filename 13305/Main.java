package com.company;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int n;
    public static long[] road;
    public static long[] cost;
    public static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        road = new long[n-1];
        cost = new long[n];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n-1; i++)
            road[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++)
            cost[i] = Integer.parseInt(st.nextToken());

        solution();
        System.out.println(result);

    }

    public static void solution(){
        long min = Integer.MAX_VALUE;

        for(int i = 0; i < n-1; i++){
            min = Math.min(min, cost[i]);
            result += road[i] * min;
        }
    }

}
