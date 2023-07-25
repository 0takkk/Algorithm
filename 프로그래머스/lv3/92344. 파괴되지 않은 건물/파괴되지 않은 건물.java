class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int n = board.length;
        int m = board[0].length;

        int[][] dp = new int[n+1][m+1];
        for (int[] sk : skill) {
            int degree = sk[0] == 1 ? -1 * sk[5] : sk[5];
            int r1 = sk[1];
            int c1 = sk[2];
            int r2 = sk[3];
            int c2 = sk[4];

            dp[r1][c1] += degree;
            dp[r1][c2+1] -= degree;
            dp[r2+1][c1] -= degree;
            dp[r2+1][c2+1] += degree;
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                dp[i][j+1] += dp[i][j];
            }
        }

        for(int j = 0; j < m; j++){
            for(int i = 0; i < n; i++){
                dp[i+1][j] += dp[i][j];
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] + dp[i][j] > 0) answer++;
            }
        }

        return answer;
    }
}