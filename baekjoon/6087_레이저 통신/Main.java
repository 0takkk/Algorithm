import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;
        int dir, mirror;

        public Pos(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public Pos(int x, int y, int dir, int mirror) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirror = mirror;
        }
    }

    public static int n, m;
    public static char[][] map;
    public static int[][] mirrors;
    public static Pos c1, c2;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'C'){
                    if(c1 == null) c1 = new Pos(i, j, -1);
                    else c2 = new Pos(i, j, -1);
                }
            }
        }

        mirrors = new int[n][m];
        for(int i = 0; i < n; i++){
            Arrays.fill(mirrors[i], Integer.MAX_VALUE);
        }
        mirrors[c1.x][c1.y] = 0;

        bfs();
        System.out.println(mirrors[c2.x][c2.y] - 1);
    }

    public static void bfs(){
        Queue<Pos> q = new LinkedList<>();
        q.offer(c1);

        while(!q.isEmpty()){
            Pos now = q.poll();
            int x = now.x;
            int y = now.y;
            int dir = now.dir;
            int mirror = now.mirror;

            if(x == c2.x && y == c2.y) continue;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == '*') continue;

                int nMirror = mirror + (i == dir ? 0 : 1);
                if(mirrors[nx][ny] >= nMirror){
                    mirrors[nx][ny] = nMirror;
                    q.offer(new Pos(nx, ny, i, nMirror));
                }
            }
        }
    }

}
