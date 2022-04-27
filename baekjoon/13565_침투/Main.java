import java.io.*;
import java.util.*;

public class Main {

    public static int m, n;
    public static int[][] map;

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];

        for(int i = 0; i < m; i++){
            String str = br.readLine();
            for(int j = 0; j < n; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for(int i = 0; i < n; i++){
            if(map[0][i] == 0){
                bfs(new Pos(0, i));
            }
        }

        boolean flag = false;
        for(int i = 0; i < n; i++){
            if(map[m-1][i] == 2){
                flag = true;
                break;
            }
        }

        if(flag) System.out.println("YES");
        else System.out.println("NO");
    }

    public static void bfs(Pos pos){
        Queue<Pos> q = new LinkedList<>();
        q.offer(pos);
        map[pos.x][pos.y] = 2;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;

                if(map[nx][ny] == 0){
                    map[nx][ny] = 2;
                    q.offer(new Pos(nx, ny));
                }
            }
        }
    }

}
