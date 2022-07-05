import java.io.*;
import java.util.*;

public class Main {

    public static class Tomato{
        int z, y, x;

        public Tomato(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }

    public static int n, m, h;
    public static int[][][] map;
    public static Queue<Tomato> q = new LinkedList<>();

    public static int[] dz = {0, 0, 0, 0, 1, -1};
    public static int[] dy = {0, 0, 1, -1, 0, 0};
    public static int[] dx = {1, -1, 0, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][n][m];
        int count = 0;

        for(int i = 0; i < h; i++){
            for(int j = 0; j < n; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < m; k++){
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if(map[i][j][k] == 0) count++;

                    if(map[i][j][k] == 1){
                        q.offer(new Tomato(i, j, k));
                    }
                }
            }
        }

        if(count == 0) {
            System.out.println(0);
            return;
        }

        System.out.println(bfs());
    }

    public static int bfs(){
        int time = -1;

        while(!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Tomato tomato = q.poll();
                int z = tomato.z;
                int y = tomato.y;
                int x = tomato.x;

                for (int d = 0; d < 6; d++) {
                    int nz = z + dz[d];
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if (!isRange(nz, ny, nx)) continue;

                    if (map[nz][ny][nx] == 0) {
                        map[nz][ny][nx] = 1;
                        q.offer(new Tomato(nz, ny, nx));
                    }
                }
            }

            time++;
        }

        if(check()) return time;
        return -1;
    }

    public static boolean check(){
        for(int i = 0; i < h; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < m; k++){
                    if(map[i][j][k] == 0) return false;
                }
            }
        }
        return true;
    }
    public static boolean isRange(int z, int y, int x){
        return z >= 0 && z < h && y >= 0 && y < n && x >= 0 && x < m;
    }
}
