package com.company;
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int n, s, cnt = 0;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " " );

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        dfs(0, 0);

        System.out.println(s == 0 ? --cnt : cnt);
    }

    public static void dfs(int idx, int sum){
        if(idx == n){
            if(s == sum)
                cnt++;
            return;
        }
        dfs(idx + 1, sum + arr[idx]);
        dfs(idx + 1, sum);
    }
}
