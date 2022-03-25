package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] T = new int[n+2];
        int[] P = new int[n+2];
        int[] dp = new int[n+2];
        int max = 0;

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine()," ");
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= n+1; i++){
            max = Math.max(max, dp[i]);

            int day = i + T[i];
            if(day <= n + 1){
                dp[day] = Math.max(dp[day], max + P[i]);
            }
        }

        System.out.println(max);
    }
}
