class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        
        int len = sticker.length;
        if(len == 1) return sticker[0];
        
        int[] dp1 = new int[len];
        dp1[0] = sticker[0];
        dp1[1] = sticker[0];
        for(int i = 2; i < len-1; i++){
            dp1[i] = Math.max(dp1[i-1], dp1[i-2] + sticker[i]);
        }
        
        int[] dp2 = new int[len];
        dp2[0] = 0;
        dp2[1] = sticker[1];
        for(int i = 2; i < len; i++){
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + sticker[i]);
        }

        return Math.max(dp1[len-2], dp2[len-1]);
    }
}
