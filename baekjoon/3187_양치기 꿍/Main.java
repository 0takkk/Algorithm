import java.io.*;
import java.util.*;

public class Main {

    public static int r, c, vCnt, kCnt;
    public static char[][] map;
    public static boolean[][] visited;
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};

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
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        visited = new boolean[r][c];

        for(int i = 0; i < r; i++){
            String str = br.readLine();
            for(int j = 0; j < c; j++){
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'v') vCnt++;
                else if(map[i][j] == 'k') kCnt++;
            }
        }

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(!visited[i][j] && map[i][j] != '#'){
                    bfs(new Pos(i, j));
                }
            }
        }

        System.out.println(kCnt + " " + vCnt);
    }

    public static void bfs(Pos pos){
        Queue<Pos> q = new LinkedList<>();
        q.offer(pos);

        int v = 0, k = 0;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            if(visited[x][y]) continue;
            visited[x][y] = true;

            if(map[x][y] == 'v') v++;
            else if(map[x][y] == 'k') k++;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if(map[nx][ny] == '#') continue;

                if(!visited[nx][ny]){
                    q.offer(new Pos(nx, ny));
                }
            }
        }

        if(v >= k) kCnt -= k;
        else vCnt -= v;
    }

}
