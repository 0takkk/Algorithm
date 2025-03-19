class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        int[] servers = new int[24+k];
        
        for(int i = 0; i < players.length; i++) {
            int player = players[i];
            int server = servers[i];
            
            int needServer = player/m;
            if(server < needServer) {
                answer += (needServer - server);
                for(int j = i; j < i+k; j++) {
                    servers[j] += (needServer - server);
                }
            }
        }
        
        return answer;
    }
}