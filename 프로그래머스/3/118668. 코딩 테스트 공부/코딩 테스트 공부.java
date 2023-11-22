class Solution {
    public static int solution(int alp, int cop, int[][] problems) {
        int maxAlp = alp;
        int maxCop = cop;

        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        int[][] dp = new int[maxAlp+2][maxCop+2];

        for(int i = alp; i <= maxAlp; i++) {
            for(int j = cop; j <= maxCop; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[alp][cop] = 1;

        for(int i = alp; i <= maxAlp; i++) {
            for(int j = cop; j <= maxCop; j++) {
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);

                for (int[] problem : problems) {
                    int reqAlp = problem[0];
                    int reqCop = problem[1];
                    int rwdAlp = problem[2];
                    int rwdCop = problem[3];
                    int cost = problem[4];

                    if(i >= reqAlp && j >= reqCop) {
                        int x = Math.min(maxAlp, i + rwdAlp);
                        int y = Math.min(maxCop, j + rwdCop);
                        dp[x][y] = Math.min(dp[x][y], dp[i][j]+cost);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop]-1;
    }
}