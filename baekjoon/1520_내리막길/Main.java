import java.io.*;
import java.util.*;

public class Main {

    public static int m, n;
    public static int[][] arr;
    public static long[][] dp;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static class Pos implements Comparable<Pos>{
        int x, y, num;

        public Pos(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public int compareTo(Pos o) {
            return this.num - o.num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new int[m+1][n+1];
        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[m+1][n+1];
        dp[m][n] = 1;

        bfs(new Pos(m, n, arr[m][n]));
        System.out.println(dp[1][1]);
    }

    public static void bfs(Pos pos){
        PriorityQueue<Pos> q = new PriorityQueue<>();
        q.offer(pos);

        while(!q.isEmpty()){
            Pos now = q.poll();
            int x = now.x;
            int y = now.y;
            int num = now.num;

            if(x == 1 && y == 1){
                continue;
            }

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(num < arr[nx][ny]){
                    if(dp[nx][ny] == 0){
                        q.offer(new Pos(nx, ny, arr[nx][ny]));
                    }
                    dp[nx][ny] += dp[x][y];
                }
            }
        }
    }

    public static boolean isRange(int x, int y){
        return x > 0 && x <= m && y > 0 && y <= n;
    }

}
