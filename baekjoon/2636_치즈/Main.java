import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, cheese;
    public static int[][] map;

    public static int[] dx = {0, 0, -1, 1};
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

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) cheese++;
            }
        }

        int cnt = 0;
        int time = 0;
        while(cheese != 0){
            cnt = cheese;
            time++;
            bfs();
        }

        System.out.println(time);
        System.out.println(cnt);
    }


    public static void bfs(){
        boolean[][] visited = new boolean[n][m];
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(0, 0));
        visited[0][0] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    if(map[nx][ny] == 0){
                        q.offer(new Pos(nx, ny));
                    } else{
                        cheese--;
                        map[nx][ny] = 0;
                    }
                }
            }
        }
    }
}
