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
    public static boolean[][] visited;
    public static Queue<Pos> q = new ArrayDeque<>();

    public static int[] dx = {-1, -1, -1, 0 ,0, 1, 1, 1};
    public static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = str.charAt(j) == '.' ? 0 : str.charAt(j) - '0';
            }
        }

        visited = new boolean[n][m];

        for(int i = 1; i < n-1; i++){
            for(int j = 1; j < m-1; j++){
                if(map[i][j] == 9 || map[i][j] == 0) continue;

                if(isWillCollapse(i, j)){
                    q.offer(new Pos(i, j));
                    visited[i][j] = true;
                }
            }
        }

        System.out.println(wave());
    }

    public static int wave(){
        int time = 0;

        while(!q.isEmpty()){
            int size = q.size();
            while(size-->0){
                Pos p = q.poll();
                int x = p.x;
                int y = p.y;

                map[x][y] = 0;

                for(int i = 0; i < 8; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(map[nx][ny] == 0 || map[nx][ny] == 9) continue;

                    if(!visited[nx][ny] && isWillCollapse(nx, ny)){
                        q.offer(new Pos(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
            time++;
        }

        return time;
    }

    public static boolean isWillCollapse(int x, int y){
        int cnt = 0;
        for(int i = 0; i < 8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(map[nx][ny] == 0) cnt++;
        }

        return cnt >= map[x][y];
    }

}
