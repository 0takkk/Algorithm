import java.io.*;
import java.util.*;

public class Main {

    public static class Pos {
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

    public static int n, m, ans;
    public static char[][] map;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 'L') {
                    bfs(new Pos(i, j));
                }
            }
        }

        System.out.println(ans);
    }

    public static void bfs(Pos pos) {
        boolean[][] visited = new boolean[n][m];
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        visited[pos.x][pos.y] = true;

        while(!q.isEmpty()) {
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int move = p.move;

            ans = Math.max(ans, move);

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny] && map[nx][ny] == 'L') {
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny, move+1));
                }
            }
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
