import java.util.*;

class Solution {
    
    public int n, m;
    public char[][] map;
    public int[] dx = {0, 0, 1, -1};
    public int[] dy = {1, -1, 0, 0};
    
    public class Pos {
        int x, y;
        
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public Queue<Pos> blank = new ArrayDeque<>();
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        n = storage.length;
        m = storage[0].length();
        map = new char[n+2][m+2];
        
        for(int i = 0; i <= n+1; i++) {
            Arrays.fill(map[i], '!');
        }
        
        for(int i = 1; i <= n; i++) {
            String str = storage[i-1]; 
            for(int j = 1; j <= m; j++) {
                map[i][j] = str.charAt(j-1);
            }
        }
        
        for(int i = 0; i <= n+1; i++) {
            for(int j = 0; j <= m+1; j++) {
                if(map[i][j] == '!') {
                    blank.add(new Pos(i,j));
                }
            }
        }
        
        for(String request : requests) {
            doClear(request);
            bfs();
        }
        
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(map[i][j] != '!') {
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    public void doClear(String request) {
        if(request.length() == 1) {
            while(!blank.isEmpty()) {
                Pos pos = blank.poll();
                int x = pos.x;
                int y = pos.y;
                
                for(int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    
                    if(isRange(nx, ny) && map[nx][ny] == request.charAt(0)) {
                        map[nx][ny] = '!';
                    }
                }
            }
        }
        else {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= m; j++) {
                    if(map[i][j] == request.charAt(0)) {
                        map[i][j] = '!';
                    }
                }
            }
        }
    }
    
    public void bfs() {
        Queue<Pos> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n+2][m+2];
        q.add(new Pos(0, 0));
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            blank.add(new Pos(x, y));            
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(isRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == '!') {
                    q.add(new Pos(nx, ny));
                    visited[nx][ny] = true;
                } 
            }
        }
    }
    
    public boolean isRange(int x, int y) {
        return 0 <= x && x <= n+1 && 0 <= y && y <= m+1;
    }
}