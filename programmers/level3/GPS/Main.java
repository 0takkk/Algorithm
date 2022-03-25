import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(7, 10, new int[][] {{1, 2}, {1, 3}, {2, 3},
                {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}, 6, new int[] {1,2,3,3,6,7}));

        System.out.println(solution(4 , 3, new int[][] {{1, 2}, {2, 3}, {3, 4}}, 4, new int[] {1,1, 1, 4}));
    }

    public static int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int[][] map = new int[n+1][n+1];
        for (int[] edge : edge_list) {
            map[edge[0]][edge[1]] = map[edge[1]][edge[0]] = 1;
        }

        int[][] dp = new int[k][n+1];
        for(int i = 0; i < k; i++)
            Arrays.fill(dp[i], k+1);
        dp[0][gps_log[0]] = 0;

        for(int i = 1; i < k; i++){
            for(int j = 1; j <= n; j++){

                dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);

                for(int node = 1; node <= n; node++){
                    if(map[j][node] == 1){
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][node]);
                    }
                }
                if(gps_log[i] != j) dp[i][j]++;

            }
        }

        if(dp[k-1][gps_log[k-1]] < k+1) return dp[k-1][gps_log[k-1]];
        else return -1;
    }

}
