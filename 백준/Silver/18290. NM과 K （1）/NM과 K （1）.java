import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, k, len, max;
    public static int[] arr, visited;

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        max = Integer.MIN_VALUE;
        len = n*m;
        arr = new int[len];
        int idx = 0;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                arr[idx++] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new int[len];

        dfs(0, 0, 0);

        System.out.println(max);
    }

    public static void dfs(int now, int sum, int cnt) {
        if(cnt == k) {
            max = Math.max(max, sum);
            return;
        }

        if(now >= len) return;

        if(visited[now] > 0) {
            dfs(now+1, sum, cnt);
            return;
        }

        dfs(now+1, sum, cnt);

        int x = now/m;
        int y = now%m;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isRange(nx, ny)) continue;

            visited[nx * m + ny]++;
        }
        dfs(now + 1, sum + arr[now], cnt + 1);
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isRange(nx, ny)) continue;

            visited[nx * m + ny]--;
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}