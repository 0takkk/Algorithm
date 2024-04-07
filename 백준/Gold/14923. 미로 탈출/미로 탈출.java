import java.io.*;
import java.util.*;

public class Main {

    public static class Pos {
        int x, y;
        int move;
        int change;

        public Pos(int x, int y, int move, int change) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.change = change;
        }
    }

    public static int n, m;
    public static Pos start, end;
    public static int[][] map;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+1][m+1];

        st = new StringTokenizer(br.readLine());
        start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0, 0);

        st = new StringTokenizer(br.readLine());
        end = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0, 0);

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        boolean[][][] visited = new boolean[n+1][m+1][2];
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.x][start.y][0] = true;

        while(!q.isEmpty()) {
            Pos p = q.poll();

            if(p.x == end.x && p.y == end.y) {
                return p.move;
            }

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(isRange(nx, ny)) {
                    if(isWall(nx, ny) && p.change == 0 && !visited[nx][ny][1]) {
                        visited[nx][ny][1] = true;
                        q.offer(new Pos(nx, ny, p.move+1, 1));
                    }
                    else if(!isWall(nx, ny) && !visited[nx][ny][p.change]) {
                        visited[nx][ny][p.change] = true;
                        q.offer(new Pos(nx, ny, p.move+1, p.change));
                    }
                }
            }

        }

        return -1;
    }

    public static boolean isRange(int x, int y) {
        return x >= 1 && x <= n && y >= 1 && y <= m;
    }

    public static boolean isWall(int x, int y) {
        return map[x][y] == 1;
    }

}