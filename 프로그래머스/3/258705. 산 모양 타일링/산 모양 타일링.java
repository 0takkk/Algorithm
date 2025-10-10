class Solution {
    public int solution(int n, int[] tops) {
        int m = 1 + 2 * n;
        
        int[][] dp = new int[m][3];
        
        dp[0][0] = 1;
        dp[1][0] = 1;
        dp[1][1] = 1;
        if(tops[0] == 1) {
            dp[1][2] = 1;
        }
        
        for(int i = 2; i < m; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 10007;
            dp[i][1] = (dp[i-2][0] + dp[i-2][1] + dp[i-2][2]) % 10007;
            if(i % 2 == 1 && tops[i/2] == 1) {
                dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 10007;
            }
        }
        
        return (dp[m-1][0] + dp[m-1][1] + dp[m-1][2]) % 10007;
    }
}