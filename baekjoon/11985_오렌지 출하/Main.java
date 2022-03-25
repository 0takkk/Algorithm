package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        int[] arr = new int[n+1];

        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        long[] dp = new long[n+1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i <= n; i++){
            long max = 0;
            long min = Long.MAX_VALUE;

            for(int j = i; j >= Math.max(1, i-m+1); j--){
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);

                dp[i] = Math.min(dp[i], dp[j-1] + k + (i-j+1) * (max - min));
            }
        }

        System.out.println(dp[n]);
    }

}
