import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, max, ans;
    public static int[][] map;
    public static boolean[][] visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;
            }
        }

//        exc();

        System.out.println(ans);
    }

    public static void dfs(int x, int y, int cnt, int sum){
        if(ans >= sum + (max * (4-cnt))) return;

        if(cnt == 4){
            ans = Math.max(ans, sum);
            return;
        }

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!isRange(nx, ny)) continue;

            if(!visited[nx][ny]){
                visited[nx][ny] = true;
                dfs(nx, ny, cnt+1, sum + map[nx][ny]);
                dfs(x, y, cnt+1, sum + map[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    // ㅜ 모양
    public static void exc(){
        // 중앙에 있는 ㅜ, ㅓ, ㅏ, ㅗ
        for(int i = 1; i < n-1; i++){
            for(int j = 1; j < m-1; j++){
                int sum = map[i][j];

                for(int k = 0; k < 4; k++){
                    sum += map[i + dx[k]][j + dy[k]];
                }

                for(int k = 0; k < 4; k++){
                    max = Math.max(max, sum - map[i + dx[k]][j + dy[k]]);
                }
            }
        }

        // 위 테두리 ㅜ, 아래 테두리 ㅗ
        for(int i = 1; i < m-1; i++){
            int upSum = map[0][i-1] + map[0][i] + map[0][i+1];
            max = Math.max(max, upSum + map[1][i]);

            int downSum = map[n-1][i-1] + map[n-1][i] + map[n-1][i+1];
            max = Math.max(max, downSum + map[n-2][i]);
        }

        // 왼쪽 테두리 ㅏ, 오른쪽 테두리 ㅓ
        for(int i = 1; i < n-1; i++){
            int leftSum = map[i-1][0] + map[i][0] + map[i+1][0];
            max = Math.max(max, leftSum + map[i][1]);

            int rightSum = map[i-1][m-1] + map[i][m-1] + map[i+1][m-1];
            max = Math.max(max, rightSum + map[i][m-2]);
        }
    }

}