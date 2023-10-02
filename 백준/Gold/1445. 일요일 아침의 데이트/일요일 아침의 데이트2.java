import java.io.*;
import java.util.*;

public class Main {

    public static class Pos implements Comparable<Pos>{
        int x, y;
        int up, side;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int up, int side) {
            this.x = x;
            this.y = y;
            this.up = up;
            this.side = side;
        }

        @Override
        public int compareTo(Pos o) {
            if(this.up == o.up) return this.side - o.side;
            return this.up - o.up;
        }
    }

    public static int n, m;
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
        Pos start = null;

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S') {
                    start = new Pos(i, j);
                }
            }
        }

        Pos result = bfs(start);
        System.out.println(result.up + " " + result.side);
    }

    public static Pos bfs(Pos start) {
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(start);
        visited[start.x][start.y] = true;

        while(!pq.isEmpty()) {
            Pos p = pq.poll();

            if(map[p.x][p.y] == 'F') {
                return p;
            }

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if(map[nx][ny] == 'g') pq.offer(new Pos(nx, ny, p.up + 1, p.side));
                    else if(map[nx][ny] == '.' && isSide(nx, ny)) pq.offer(new Pos(nx, ny, p.up, p.side+1));
                    else pq.offer(new Pos(nx, ny, p.up, p.side));
                }
            }
        }

        return null;
    }

    public static boolean isSide(int x, int y) {
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!isRange(nx, ny)) continue;

            if(map[nx][ny] == 'g') return true;
        }

        return false;
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
