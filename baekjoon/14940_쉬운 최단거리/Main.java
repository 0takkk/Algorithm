import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;
        int move;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    public static int n, m;
    public static int[][] map, dist;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dist = new int[n][m];

        Pos start = null;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) start = new Pos(i, j);
            }
        }

        bfs(start);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                sb.append(dist[i][j] == 0 ? (map[i][j] == 1 ? -1 : 0) : dist[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void bfs(Pos start){
        boolean[][] visited = new boolean[n][m];
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.x][start.y] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int move = p.move;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny] && map[nx][ny] == 1){
                    dist[nx][ny] = move+1;
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny, move+1));
                }
            }
        }

    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
