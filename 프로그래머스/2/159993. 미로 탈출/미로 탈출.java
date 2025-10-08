import java.util.*;

class Solution {

    public static class Pos {
        int x, y;
        int lever;
        int move;
        
        public Pos(int x, int y, int lever, int move) {
            this.x = x;
            this.y = y;
            this.lever = lever;
            this.move = move;
        }
    }
    
    public static int n, m;
    public static char[][] map;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};
    
    public static int solution(String[] maps) {
        int answer = 0;
        
        n = maps.length;
        m = maps[0].length();
        map = new char[n][m];
        
        Pos start = null;
        for(int i = 0; i < n; i++) {
            map[i] = maps[i].toCharArray();
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 'S') {
                    start = new Pos(i, j, 0, 0);
                }
            }
        }
        
        answer = bfs(start);
        
        return answer;
    }
    
    public static int bfs(Pos start) {
        boolean[][][] visited = new boolean[n][m][2];
        Queue<Pos> q = new LinkedList<>();
        q.offer(start);
        visited[start.x][start.y][0] = true;
        
        while(!q.isEmpty()) {
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int lever = p.lever;
            int move = p.move;
            
            if(map[x][y] == 'E' && lever == 1) {
                return move;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nLever = lever;
                
                if(isRange(nx, ny) && !visited[nx][ny][lever] && map[nx][ny] != 'X') {
                    nLever = map[nx][ny] == 'L' ? 1 : lever;
                    visited[nx][ny][nLever] = true;
                    q.offer(new Pos(nx, ny, nLever, move+1));
                }
            }
        }
        
        return -1;
    }
    
    public static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}