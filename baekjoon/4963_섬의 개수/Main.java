import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static int[][] map;
    public static boolean[][] visited;

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            map = new int[n][m];
            visited = new boolean[n][m];

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < m; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cnt = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(map[i][j] == 1 && !visited[i][j]){
                        cnt += bfs(i, j);
                    }
                }
            }

            sb.append(cnt + "\n");
        }

        System.out.println(sb.toString());
    }

    public static int bfs(int i, int j){
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(i, j));
        visited[i][j] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            for(int k = 0; k < 8; k++){
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if(!visited[nx][ny] && map[nx][ny] == 1){
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny));
                }
            }
        }

        return 1;
    }

}
