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

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

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
                map[i][j] = dirToNum(str.charAt(j));
            }
        }

        int ans = 0;
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!visited[i][j]){
                    bfs(new Pos(i, j));
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }

    public static void bfs(Pos pos){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        visited[pos.x][pos.y] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int dir = map[x][y];

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(!visited[nx][ny]){
                visited[nx][ny] = true;
                q.offer(new Pos(nx, ny));
            }

            for(int i = 0; i < 4; i++){
                int nnx = x + dx[i];
                int nny = y + dy[i];

                if(!isRange(nnx, nny)) continue;

                if(!visited[nnx][nny] && ((nnx + dx[map[nnx][nny]]) == x) && ((nny + dy[map[nnx][nny]]) == y)){
                    visited[nnx][nny] = true;
                    q.offer(new Pos(nnx, nny));
                }
            }
        }
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static int dirToNum(char dir){
        if(dir == 'U') return 0;
        else if(dir == 'D') return 1;
        else if(dir == 'L') return 2;
        else return 3;
    }

}
