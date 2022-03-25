import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, max;
    public static int[][] map;
    public static boolean[][] visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    count++;
                    bfs(i, j);
                }
            }
        }

        System.out.println(count);
        System.out.println(max);
    }

    public static void bfs(int i, int j){
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(i, j));
        visited[i][j] = true;
        int area = 0;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            area++;

            for(int d = 0; d < 4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if(!visited[nx][ny] && map[nx][ny] == 1){
                    q.offer(new Pos(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        max = Math.max(max, area);
    }

}
