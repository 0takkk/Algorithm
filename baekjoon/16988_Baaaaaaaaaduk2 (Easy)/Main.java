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


    public static int n, m, ans;
    public static int[][] map;
    public static int[] selected;
    public static boolean[][] visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception{
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
            }
        }

        selected = new int[2];

        backtracking(0, 0);
        System.out.println(ans);
    }

    public static void backtracking(int idx, int cnt){
        if(cnt == 2){
            check();
            return;
        }

        for(int i = idx; i < n*m; i++){
            int x = i / m;
            int y = i % m;

            if(map[x][y] == 0){
                selected[cnt] = i;
                backtracking(i+1, cnt+1);
            }
        }
    }

    public static void check(){
        int x1 = selected[0] / m;
        int y1 = selected[0] % m;
        int x2 = selected[1] / m;
        int y2 = selected[1] % m;

        map[x1][y1] = map[x2][y2] = 1;

        int sum = 0;
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!visited[i][j] && map[i][j] == 2){
                    sum += bfs(new Pos(i, j));
                }
            }
        }

        ans = Math.max(ans, sum);
        map[x1][y1] = map[x2][y2] = 0;
    }

    public static int bfs(Pos pos){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        visited[pos.x][pos.y] = true;

        int cnt = 1;
        boolean flag = true;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(map[nx][ny] == 0) flag = false;

                if(!visited[nx][ny] && map[nx][ny] == 2){
                    visited[nx][ny] = true;
                    cnt++;
                    q.offer(new Pos(nx, ny));
                }
            }
        }

        if(flag) return cnt;
        return 0;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
