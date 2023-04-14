import java.util.*;

class Solution {
    
    public static class Pos{
        int x, y;
        int move;
        
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public Pos(int x, int y, int move){
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
    
    public static int n, m;
    public static char[][] map;
    public static boolean[][] visited;
    
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};
    
    public int solution(String[] board) {
        int answer = 0;
        
        n = board.length;
        m = board[0].length();
        map = new char[n][m];
        
        visited = new boolean[n][m];
        
        Pos start = null;
        
        for(int i = 0; i < n; i++){
            String str = board[i];
            for(int j = 0; j < m; j++){
                map[i][j] = str.charAt(j);
                
                if(map[i][j] == 'R'){
                    start = new Pos(i, j);
                    map[i][j] = '.';
                    visited[i][j] = true;
                }
            }
        }
        
        return bfs(start);
    }
    
    public static int bfs(Pos start){
        Queue<Pos> q = new LinkedList<>();
        q.offer(start);
        
        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int move = p.move;
            
            if(map[x][y] == 'G'){
                return move;
            }
            
            for(int i = 0; i < 4; i++){
                int nx = x;
                int ny = y;
                
                while(isRange(nx, ny) && map[nx][ny] != 'D'){
                    nx += dx[i];
                    ny += dy[i];
                }
                
                nx -= dx[i];
                ny -= dy[i];
                
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny, move+1));
                }
                
            }
        }
        
        return -1;
    }
    
    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
