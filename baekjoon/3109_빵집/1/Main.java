import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, ans;
    public static char[][] map;
    public static boolean[][] visited;
    public static boolean flag;

    public static int[] dx = {-1, 0, 1};
    public static int[] dy = {1, 1, 1};  // 오른쪽 위, 오른쪽, 오른쪽 아래 순으로 방문

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

        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){  // 0열 모든 행에서 출발
            flag = false;
            dfs(i, 0);
        }

        System.out.println(ans);
    }

    public static void dfs(int x, int y){
        if(y == m-1){  // m-1 열에 도착했으면
            flag = true;  // flag를 true로 변경해서 이번 dfs에서는 더 이상 방문하지 않도록 처리
            ans++;
            return;
        }

        for(int i = 0; i < 3; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!isRange(nx, ny)) continue;

            if(!visited[nx][ny] && map[nx][ny] == '.'){
                visited[nx][ny] = true;  // 방문 처리해서 더 이상 방문하지 않도록 처리
                dfs(nx, ny);
                if(flag) return;  // flag가 true이면 더이상 방문 x
                //visited[nx][ny] = false;  // 방문 처리를 해제시켰을 때 시간초과 발생
            }
        }
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
