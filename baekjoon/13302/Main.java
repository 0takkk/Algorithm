package com.company;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int n, m;
    public static boolean[] vacation;
    public static int[][] dp;    // dp[i][j] => i일까지 j개의 쿠폰 가지고 있는 최소 가격

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        vacation = new boolean[n+1];
        for(int i = 1; i <= n; i++)
            vacation[i] = true;

        if(m > 0){
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < m; i++)
                vacation[Integer.parseInt(st.nextToken())] = false;
        }

        dp = new int[n+1][n+1];
        for(int i = 0; i <= n; i++)
            Arrays.fill(dp[i], -1);

        System.out.println(solution(1, 0));

    }

    public static int solution(int day, int coupon){
        if(day > n) return 0;
        if(dp[day][coupon] != -1) return dp[day][coupon];

        dp[day][coupon] = Integer.MAX_VALUE;
        if(vacation[day]){
            if(coupon >= 3){
                dp[day][coupon] = Math.min(dp[day][coupon], solution(day+1, coupon-3));
            }
            dp[day][coupon] = Math.min(dp[day][coupon], solution(day+1, coupon) + 10000);
            dp[day][coupon] = Math.min(dp[day][coupon], solution(day+3, coupon+1) + 25000);
            dp[day][coupon] = Math.min(dp[day][coupon], solution(day+5, coupon+2) + 37000);
        }
        else{
            return dp[day][coupon] = Math.min(dp[day][coupon], solution(day+1, coupon));
        }

        return dp[day][coupon];
    }

}
