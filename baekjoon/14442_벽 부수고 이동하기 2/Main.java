import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;
        int broken, dist;

        public Pos(int x, int y, int broken, int dist) {
            this.x = x;
            this.y = y;
            this.broken = broken;
            this.dist = dist;
        }
    }

    public static int n, m, k;
    public static int[][] map, visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1 ,0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];
        visited = new int[n+1][m+1];
        for(int i = 1; i <= n; i++){
            String str = br.readLine();
            for(int j = 1; j <= m; j++){
                map[i][j] = str.charAt(j-1)-'0';
                if(map[i][j] == 0) visited[i][j] = -1;
            }
        }

        System.out.println(bfs());
    }

    public static int bfs(){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(1, 1, k, 1));
        visited[1][1] = k;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int broken = p.broken;
            int dist = p.dist;

            if(x == n && y == m){
                return dist;
            }

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;
                if(visited[nx][ny] >= broken) continue;

                if(map[nx][ny] == 1) q.offer(new Pos(nx, ny, broken-1, dist+1));
                else q.offer(new Pos(nx, ny, broken, dist+1));

                visited[nx][ny] = broken;
            }
        }

        return -1;
    }

    public static boolean isRange(int x, int y){
        return x > 0 && x <= n && y > 0 && y <= m;
    }

}
