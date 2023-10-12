class Solution {
    public static int MOD = 20170805;
    public static final int STREET = 0, SIDEWALK = 1, PROTECT = 2;

    public static int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[2][m][n];
        dp[0][0][0] = dp[1][0][0] = 1;

        for(int j = 1; j < n; j++) {
            if(cityMap[0][j] == SIDEWALK) continue;
            dp[0][0][j] = dp[0][0][j-1];
        }

        for(int i = 1; i < m; i++) {
            if(cityMap[i][0] == SIDEWALK) continue;
            dp[1][i][0] = dp[1][i-1][0];
        }


        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(cityMap[i][j-1] == STREET) {
                    dp[0][i][j] = (dp[0][i][j-1] + dp[1][i][j-1]) % MOD;
                }
                else if(cityMap[i][j-1] == PROTECT) {
                    dp[0][i][j] = dp[0][i][j-1];
                }

                if(cityMap[i-1][j] == STREET) {
                    dp[1][i][j] = (dp[0][i-1][j] + dp[1][i-1][j]) % MOD;
                }
                else if(cityMap[i-1][j] == PROTECT) {
                    dp[1][i][j] = dp[1][i-1][j];
                }
            }
        }

        return (dp[0][m-1][n-1] + dp[1][m-1][n-1]) % MOD;
    }
}