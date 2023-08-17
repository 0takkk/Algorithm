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

    public static int n, m;
    public static int[][] map;
    public static int[] moves;

    public static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    public static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        moves = new int[m+1];

        st = new StringTokenizer(br.readLine());
        Pos start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = i;
        }

        bfs(start);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= m; i++) {
            sb.append(moves[i]).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void bfs(Pos start) {
        boolean[][] visited = new boolean[n+1][n+1];
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.x][start.y] = true;

        while (!q.isEmpty()) {
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int move = p.move;

            if(map[x][y] != 0) {
                moves[map[x][y]] = move;
            }

            for(int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny, move+1));
                }
            }
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 1 && x <= n && y >= 1 && y <= n;
    }

}
