import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;
        int count, dist;

        public Pos(int x, int y, int count, int dist) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.dist = dist;
        }
    }

    public static int k, n, m;
    public static int[][] map;

    public static int[] dx = {0, 0, 1, -1, -1, -2, -2, -1, 1, 2, 2, 1};
    public static int[] dy = {1, -1, 0, 0, -2, -1, 1, 2, -2, -1, 1, 2};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    public static int bfs(){
        int[][] visited = new int[n][m];
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0, k, 0));

        for(int i = 0; i < n; i++){
            Arrays.fill(visited[i], -1);
        }
        visited[0][0] = k;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int count = p.count;
            int dist = p.dist;

            if(x == n-1 && y == m-1) return dist;

            for(int i = 0; i < 12; i++){
                if(i >= 4 && count <= 0) continue;

                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                int newCount = i >= 4 ? count-1 : count;
                if(map[nx][ny] != 1 && visited[nx][ny] < newCount){
                    visited[nx][ny] = newCount;
                    q.offer(new Pos(nx, ny, newCount, dist+1));
                }
            }
        }

        return -1;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
