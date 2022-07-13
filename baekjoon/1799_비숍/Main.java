import java.io.*;
import java.util.*;

public class Main {

    public static int n, ans[];
    public static int[][] map;
    public static boolean[][] visited, isBlack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        isBlack = new boolean[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                isBlack[i][j] = (i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1);
            }
        }

        ans = new int[2];

        dfs(0, true, 0);
        dfs(0, false, 0);

        System.out.println(ans[0] + ans[1]);
    }

    public static void dfs(int idx, boolean black, int cnt){
        for(int i = idx; i < n * n; i++){
            int x = i / n;
            int y = i % n;

            if(map[x][y] == 0 || isBlack[x][y] != black || !check(x, y)) continue;

            visited[x][y] = true;
            dfs(i+1, black, cnt+1);
            visited[x][y] = false;
        }

        ans[black ? 0 : 1] = Math.max(ans[black ? 0 : 1], cnt);
    }

    public static boolean check(int x, int y){
        int[] dx = {-1, -1};
        int[] dy = {-1, 1};

        for(int i = 0; i < 2; i++){
            int nx = x;
            int ny = y;

            while(true){
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) break;
                if(visited[nx][ny]) return false;

                nx += dx[i];
                ny += dy[i];
            }
        }

        return true;
    }

}
