import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[][] map;

    public static int[] dx = {0, 1};
    public static int[] dy = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[n][n];
        dp[0][0] = 1;

        for(int x = 0; x < n; x++) {
            for(int y = 0; y < n; y++) {
                if(dp[x][y] > 0) {
                    for(int d = 0; d < 2; d++) {
                        int nx = x + map[x][y] * dx[d];
                        int ny = y + map[x][y] * dy[d];

                        if(isRange(nx, ny) && !(x == nx && y == ny)) {
                            dp[nx][ny] += dp[x][y];
                        }
                    }
                }
            }
        }

        System.out.println(dp[n-1][n-1]);
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

}