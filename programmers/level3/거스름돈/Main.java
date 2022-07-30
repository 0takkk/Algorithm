class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
    
        int[] dp = new int[n+1];
        dp[0] = 1;
        
        for(int m : money){
            for(int i = 1; i <= n; i++){
                if(i >= m){
                    dp[i] += dp[i-m];
                }
            }
        }
        
        return dp[n];
    }
}
