class Solution {
    
    public final int DOWN = 0;
    public final int RIGHT = 1;
    public final int CROSS = 2;
    
    public int[] solution(int n) {
        int len = 0;
        for(int i = 1; i <= n; i++) {
            len += i;
        }
        
        int[] answer = new int[len];
        
        int[][] arr = new int[n][n];
        
        int num = 1;
        
        int x = 0;
        int y = 0;
        int dir = 0;
        
        while(num <= len) {
            arr[x][y] = num;
            
            int nx = x;
            int ny = y;
            
            if(dir == DOWN) {
                nx = x + 1;
                ny = y;
                
                if(!isRange(nx, ny, n) || arr[nx][ny] != 0) {
                    dir = RIGHT;
                }
            }
            
            if(dir == RIGHT) {
                nx = x;
                ny = y + 1;
                
                if(!isRange(nx, ny, n) || arr[nx][ny] != 0) {
                    dir = CROSS;
                }
            }
            
            if(dir == CROSS) {
                nx = x-1;
                ny = y-1;
                
                if(!isRange(nx, ny, n) || arr[nx][ny] != 0) {
                    dir = DOWN;
                    nx = x+1;
                    ny = y;
                }
            }
            
            x = nx;
            y = ny;
            num++;
        }
        
        int idx = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                answer[idx++] = arr[i][j];
            }
        }
        
        return answer;
    }
    
    public boolean isRange(int x, int y, int n) {
        return 0 <= x && x < n && 0 <= y && y <= x;
    }
}