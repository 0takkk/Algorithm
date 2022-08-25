import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int n, m, blank, ans;
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
                if(map[i][j] == 0) blank++;
            }
        }

        building(0, 0);
        System.out.println(ans);
    }

    public static void building(int idx, int cnt){
        if(cnt == 3){
            ans = Math.max(ans, blank-3-spreadVirus());
            return;
        }

        for(int i = idx; i < n*m; i++){
            int x = i / m;
            int y = i % m;

            if(map[x][y] == 0){
                map[x][y] = 1;
                building(i+1, cnt+1);
                map[x][y] = 0;
            }
        }
    }

    public static int spreadVirus(){
        int cnt = 0;
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 2 && !visited[i][j]){
                    cnt += bfs(new Pos(i,j));

                    if(blank-3-cnt < ans) return cnt;
                }
            }
        }

        return cnt;
    }

    public static int bfs(Pos pos){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        visited[pos.x][pos.y] = true;

        int cnt = 0;
        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny] && map[nx][ny] == 0){
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny));
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
