class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        int[] dp = new int[24+k];
        
        for(int i = 0; i < 24; i++) {
            int player = players[i]-m;
            
            if(player >= dp[i]) {
                int diff = player - dp[i];
                int count = diff / m + 1;
                
                answer += count;
                
                for(int j = i; j < i+k; j++) {
                    dp[j] += (m * count);
                }
            }
        }
        
        return answer;
    }
}