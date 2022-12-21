import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;

    public static int[] dx = {0, 1, 1};
    public static int[] dy = {1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int[][] dp = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = map[0][0];

        for(int x = 0; x < n; x++){
            for(int y = 0; y < m; y++){
                for(int k = 0; k < 3; k++){
                    int nx = x + dx[k];
                    int ny = y + dy[k];

                    if(!isRange(nx, ny)) continue;

                    dp[nx][ny] = Math.max(dp[nx][ny], dp[x][y] + map[nx][ny]);
                }
            }
        }

        System.out.println(dp[n-1][m-1]);
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
