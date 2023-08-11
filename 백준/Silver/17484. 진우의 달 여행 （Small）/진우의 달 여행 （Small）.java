import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static int[][] map;
    public static int ans = Integer.MAX_VALUE;

    public static int[] dx = {1, 1, 1};
    public static int[] dy = {-1, 0, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < m; i++) {
            dfs(0, i, -1, map[0][i]);
        }

        System.out.println(ans);
    }

    public static void dfs(int x, int y, int preDir, int sum) {
        if(sum >= ans) return;

        if(x == n-1) {
            ans = Math.min(ans, sum);
            return;
        }

        for(int dir = 0; dir < 3; dir++) {
            if(dir == preDir) continue;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(isRange(nx, ny)) {
                dfs(nx, ny, dir, sum + map[nx][ny]);
            }
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
