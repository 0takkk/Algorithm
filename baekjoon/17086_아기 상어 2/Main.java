import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static int[][] map;
    public static boolean[][] visited;
    public static Queue<Pos> q;

    public static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static class Pos{
        int x, y, num;

        public Pos(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
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
        q = new LinkedList<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1) {
                    q.offer(new Pos(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    public static int bfs(){
        int result = 0;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int num = p.num;

            result = Math.max(result, num);

            for(int i = 0; i < 8; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny, num+1));
                }
            }
        }

        return result;
    }
}
