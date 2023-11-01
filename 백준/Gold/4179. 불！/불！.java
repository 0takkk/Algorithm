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
    public static char[][] map;

    public static Queue<Pos> fires;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        fires = new ArrayDeque<>();
        Pos start = null;

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'J') {
                    start = new Pos(i, j);
                }
                else if(map[i][j] == 'F') {
                    fires.offer(new Pos(i, j));
                }
            }
        }

        int move = bfs(start);

        System.out.println(move == -1 ? "IMPOSSIBLE" : move);
    }

    public static int bfs(Pos start) {
        boolean[][] visited = new boolean[n][m];
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.x][start.y] = true;

        while(!q.isEmpty()) {

            int size = q.size();
            while(size-->0) {
                Pos p = q.poll();
                int x = p.x;
                int y = p.y;
                int move = p.move;

                if (map[x][y] == 'F') continue;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (isEscape(nx, ny)) return move + 1;

                    if (!visited[nx][ny] && map[nx][ny] == '.') {
                        visited[nx][ny] = true;
                        q.offer(new Pos(nx, ny, move + 1));
                    }
                }
            }

            size = fires.size();
            while(size-->0) {
                Pos fire = fires.poll();

                for(int i = 0; i < 4; i++) {
                    int nfx = fire.x + dx[i];
                    int nfy = fire.y + dy[i];

                    if(isEscape(nfx, nfy)) continue;

                    if(map[nfx][nfy] != '#') {
                        if(map[nfx][nfy] != 'F') {
                            fires.offer(new Pos(nfx, nfy));
                        }
                        map[nfx][nfy] = 'F';
                    }
                }
            }
        }

        return -1;
    }

    public static boolean isEscape(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

}