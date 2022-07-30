import java.util.*;

class Solution {
    
    public static int[][] map = new int[102][102];
    
    public static int[] dx = {0, 0, 1, -1, -1, -1, 1, 1};
    public static int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};
    
    public static class Pos{
        int x, y;
        int cnt;
        
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
        
         public Pos(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
        
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        
        for(int[] rec : rectangle){
            int x1 = rec[0] * 2;
            int y1 = rec[1] * 2;
            int x2 = rec[2] * 2;
            int y2 = rec[3] * 2;
            
            for(int i = y1; i <= y2; i++){
                for(int j = x1; j <= x2; j++){
                    map[i][j] = 1;
                }
            }
        }
        
        return bfs(characterX*2, characterY*2, itemX*2, itemY*2);
    }
    
    public static int bfs(int characterX, int characterY, int itemX, int itemY){
        boolean[][] visited = new boolean[102][102];
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(characterX, characterY));
        visited[characterY][characterX] = true;
        
        while(!q.isEmpty()){
            Pos p = q.poll();
            int y = p.y;
            int x = p.x;
            int cnt = p.cnt;
            
            if(x == itemX && y == itemY) return cnt/2;
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(!isRange(nx, ny)) continue;
                if(!check(nx, ny)) continue;
                
                if(!visited[ny][nx] && map[ny][nx] == 1){
                    visited[ny][nx] = true;
                    q.offer(new Pos(nx, ny, cnt+1));
                }
                
            }
            
        }
        return -1;
    }
    
    public static boolean check(int x, int y){
        for(int i = 0; i < 8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(map[ny][nx] == 0) return true;
        }
        return false;
    }
    
    public static boolean isRange(int x, int y){
        return x > 0 && x <= 100 && y > 0 && y <= 100;
    }
}
