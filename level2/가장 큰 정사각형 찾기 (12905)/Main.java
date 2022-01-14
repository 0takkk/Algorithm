package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}}));
    }

    public static int solution(int [][]board) {
        int answer = 0;

        int r = board.length;
        int c = board[0].length;

        int[][] dp = new int[r+1][c+1];
        int max = 0;

        for(int i = 0; i < r; i++)
            for(int j = 0; j < c; j++)
                dp[i+1][j+1] = board[i][j];


        for(int i = 1; i <= r; i++){
            for(int j = 1; j <= c; j++){

                if(dp[i][j] == 1){
                    int up = dp[i-1][j];
                    int left = dp[i][j-1];
                    int cross = dp[i-1][j-1];

                    int min = Math.min(up, Math.min(left, cross));
                    dp[i][j] = min+1;
                    max = Math.max(max, min+1);
                }
            }
        }

        answer = max * max;

        return answer;
    }

}
