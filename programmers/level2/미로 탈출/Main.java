import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        System.out.println(solution(new String[] {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"}));
    }

    public static class Pos{
        int x, y;
        int move, hasLever;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int move, int hasLever) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.hasLever = hasLever;
        }
    }

    public static int n, m;
    public static char[][] map;
    public static Pos start;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();

        map = new char[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = maps[i].charAt(j);
                if(map[i][j] == 'S') start = new Pos(i, j);
            }
        }

        return bfs();
    }

    public static int bfs(){
        boolean[][][] visited = new boolean[2][n][m];
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);
        visited[0][start.x][start.y] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int move = p.move;
            int hasLever = p.hasLever;

            if(map[x][y] == 'E' && hasLever == 1){
                return move;
            }

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                int newHasLever = hasLever;

                if(!isRange(nx, ny)) continue;

                if(map[nx][ny] == 'L') newHasLever = 1;

                if(!visited[newHasLever][nx][ny] && map[nx][ny] != 'X'){
                    visited[newHasLever][nx][ny] = true;
                    q.offer(new Pos(nx, ny, move+1, newHasLever));
                }
            }
        }

        return -1;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
