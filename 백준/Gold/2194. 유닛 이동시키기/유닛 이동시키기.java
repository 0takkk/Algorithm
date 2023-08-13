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

    public static int n, m, a, b;
    public static boolean[][] map;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new boolean[n+1][m+1];
        while(k-->0) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }

        st = new StringTokenizer(br.readLine());
        Pos start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        st = new StringTokenizer(br.readLine());
        Pos end = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        System.out.println(bfs(start, end));
    }

    public static int bfs(Pos start, Pos end) {
        boolean[][] visited = new boolean[n+1][m+1];
        Queue<Pos> q = new ArrayDeque<>();
        visited[start.x][start.y] = true;
        q.offer(start);

        while(!q.isEmpty()) {
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int move = p.move;

            if(x == end.x && y == end.y) {
                return move;
            }

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny, move+1));
                }
            }
        }

        return -1;
    }

    public static boolean isRange(int x, int y) {
        for(int i = x; i < x+a; i++) {
            if(i < 1 || i > n) return false;
        }

        for(int j = y; j < y+b; j++) {
            if(j < 1 || j > m) return false;
        }

        for(int i = x; i < x+a; i++) {
            for(int j = y; j < y+b; j++) {
                if(map[i][j]) return false;
            }
        }

        return true;
    }

}
