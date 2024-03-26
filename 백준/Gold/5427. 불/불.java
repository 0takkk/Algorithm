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
    public static char[][] map;
    public static Queue<Pos> fires;

    public static int dx[] = {0, 0, 1, -1};
    public static int dy[] = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            map = new char[n][m];
            fires = new ArrayDeque<>();
            Pos start = null;

            for(int i = 0; i < n; i++) {
                String str = br.readLine();
                for(int j = 0; j < m; j++) {
                    map[i][j] = str.charAt(j);
                    if(map[i][j] == '@') {
                        start = new Pos(i, j, 0);
                    }
                    else if(map[i][j] == '*') {
                        fires.offer(new Pos(i, j));
                    }
                }
            }

            int result = bfs(start);
            sb.append(result == -1 ? "IMPOSSIBLE" : result).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int bfs(Pos start) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);
        boolean[][] visited = new boolean[n][m];
        visited[start.x][start.y] = true;

        while(!q.isEmpty()) {
            int size = fires.size();
            while(size-->0) {
                Pos fire = fires.poll();

                for(int i = 0; i < 4; i++) {
                    int nfx = fire.x + dx[i];
                    int nfy = fire.y + dy[i];

                    if(isRange(nfx, nfy) && map[nfx][nfy] != '#' && map[nfx][nfy] != '*') {
                        fires.offer(new Pos(nfx, nfy));
                        map[nfx][nfy] = '*';
                    }
                }
            }

            size = q.size();
            while(size-->0) {
                Pos p = q.poll();

                for(int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];

                    if(!isRange(nx, ny)) {
                        return p.move+1;
                    }

                    if(!visited[nx][ny] && map[nx][ny] == '.') {
                        visited[nx][ny] = true;
                        q.offer(new Pos(nx, ny, p.move+1));
                    }
                }
            }
        }

        return -1;
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}