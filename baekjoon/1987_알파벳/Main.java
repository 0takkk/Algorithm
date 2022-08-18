import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, ans;
    public static char[][] map;
    public static boolean[] alpa;  // 알파벳마다 밟았는지 확인

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = str.charAt(j);
            }
        }

        alpa = new boolean[26];
        alpa[map[0][0]-'A'] = true;  // 맨 처음 위치 true

        dfs(0, 0, 1);
        System.out.println(ans);
    }

    public static void dfs(int x, int y, int cnt){
        ans = Math.max(ans, cnt);  // 매 dfs마다 최댓값 비교

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!isRange(nx, ny)) continue;  // 범위를 벗어나면 실행x

            int idx = map[nx][ny] - 'A';  // 다음 위치의 알파벳 번호
            if(!alpa[idx]){  // 다음 위치의 알파벳을 안 밟았다면
                alpa[idx] = true;
                dfs(nx, ny, cnt+1);  // dfs
                alpa[idx] = false;
            }
        }
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
