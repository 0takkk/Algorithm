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

    public static int n, m;
    public static int[][] map;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        int cheese = 0;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) cheese++;
            }
        }

        System.out.println(bfs(cheese));
    }

    public static int bfs(int cheese){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0));
        int time = 0;

        while(cheese > 0){
            int size = q.size();
            while(size-->0){
                Pos p = q.poll();
                map[p.x][p.y] = 10;
                q.offer(p);
            }

            while(!q.isEmpty()){
                Pos p = q.poll();
                int x = p.x;
                int y = p.y;

                for(int i = 0; i < 4; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(!isRange(nx, ny)) continue;

                    if(map[nx][ny] == 0){
                        map[nx][ny] = 10;
                        q.offer(new Pos(nx, ny));
                    }
                }
            }

            for(int i = 1; i < n-1; i++){
                for(int j = 1; j < m-1; j++){
                    if(map[i][j] == 1){
                        int sum = 0;
                        for(int k = 0; k < 4; k++){
                            sum += map[i+dx[k]][j+dy[k]];
                        }

                        if(sum >= 20) q.offer(new Pos(i, j));
                    }
                }
            }

            cheese -= q.size();
            time++;
        }

        return time;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
