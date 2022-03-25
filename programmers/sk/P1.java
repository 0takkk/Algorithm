package com.company;

import java.util.Arrays;

public class P1 {
    public static void main(String[] args) {
        System.out.println(solution(4578, new int[] {1, 4, 99, 35, 50, 1000}));
    }

    public static  int solution(int money, int[] costs) {
        int answer = 0;

        int[] dp = new int[money+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        int[] coin = {1, 5, 10, 50, 100, 500};

        for(int i = 1; i <= money; i++){
            for(int j = 0; j < 6; j++){
                if(i >= coin[j]){
                    dp[i] = Math.min(dp[i], dp[i-coin[j]] + costs[j]);
                }
            }
        }

        answer = dp[money];

        return answer;
    }
}
