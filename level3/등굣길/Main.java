package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(4, 3, new int[][]{{2, 2}}));
        System.out.println(solution(4, 3, new int[][]{{1, 2}, {2, 1}}));
        System.out.println(solution(4, 3, new int[][]{{1, 2}}));
    }

    public static int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        int[][] dp = new int[n+1][m+1];
        dp[1][1] = 1;

        boolean[][] possible = new boolean[n+1][m+1];
        for(int i = 1; i <= n; i++){
            Arrays.fill(possible[i], true);
        }

        for (int[] puddle : puddles) {
            int y = puddle[0];
            int x = puddle[1];

            possible[x][y] = false;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){

                if(!possible[i][j]) continue;
                if(i == n && j == m) break;

                if(i != n && possible[i+1][j]){
                    dp[i+1][j] += (dp[i][j] % 1000000007);
                }

                if(j != m && possible[i][j+1]){
                    dp[i][j+1] += (dp[i][j] % 1000000007);
                }
            }
        }

        answer = dp[n][m] % 1000000007;

        return answer;
    }

}
