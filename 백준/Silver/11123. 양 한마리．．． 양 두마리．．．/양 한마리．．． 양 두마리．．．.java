import java.io.*;
import java.util.*;

public class Main {

    public static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int n, m;
    public static char[][] map;
    public static boolean[][] visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new char[n][m];
            visited = new boolean[n][m];

            for(int i = 0; i < n; i++) {
                map[i] = br.readLine().toCharArray();
            }

            int ans = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(isSheep(i, j) && !visited[i][j]) {
                        ans += bfs(new Pos(i, j));
                    }
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int bfs(Pos pos) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        visited[pos.x][pos.y] = true;

        while(!q.isEmpty()) {
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(isRange(nx, ny) && isSheep(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny));
                }
            }
        }

        return 1;
    }

    private static boolean isSheep(int nx, int ny) {
        return map[nx][ny] == '#';
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}