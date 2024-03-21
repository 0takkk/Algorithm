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
    public static int[][] map;
    public static int[] parent;

    public static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        parent = new int[n*m];
        for(int i = 0; i < n*m; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(parent[i*m+j] == i*m+j) {
                    dfs(new Pos(i, j));
                }
            }
        }

        int[] result = new int[n*m];
        for(int i = 0; i < n*m; i++) {
            result[find(i)]++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                sb.append(result[i*m + j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(Pos pos) {
        int x = pos.x;
        int y = pos.y;

        int minVal = map[x][y];
        int minX = x;
        int minY = y;

        for(int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isRange(nx, ny) && minVal > map[nx][ny]) {
                minVal = map[nx][ny];
                minX = nx;
                minY = ny;
            }
        }

        if(minVal < map[x][y]) {
            parent[x*m + y] = minX*m + minY;
            if(parent[minX*m + minY] == minX*m + minY) {
                dfs(new Pos(minX, minY));
            }
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}