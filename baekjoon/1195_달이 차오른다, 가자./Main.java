import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y, move, hasKeys;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int move, int hasKeys) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.hasKeys = hasKeys;
        }
    }

    public static int n, m;
    public static char[][] map;
    public static Pos minsik;
    public static boolean[][][] visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == '0') minsik = new Pos(i, j);
            }
        }

        visited = new boolean[64][n][m];

        System.out.println(bfs());
    }

    public static int bfs(){
        Queue<Pos> q = new LinkedList<>();
        q.offer(minsik);
        visited[0][minsik.x][minsik.y] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int move = p.move;

            if(map[x][y] == '1') return move;

            for(int i = 0; i < 4; i++){
                int hasKeys = p.hasKeys;
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny) || map[nx][ny] == '#') continue;
                if(isKey(nx, ny)){
                    int key = map[nx][ny] - 'a';
                    hasKeys = hasKeys | (1 << key);
                }

                if(isDoor(nx, ny)){
                    if(!isHasKey(nx, ny, hasKeys)) continue;
                }

                if(!visited[hasKeys][nx][ny]){
                    visited[hasKeys][nx][ny] = true;
                    q.offer(new Pos(nx, ny, move+1, hasKeys));
                }

            }
        }

        return -1;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static boolean isKey(int x, int y){
        int c = map[x][y] - 'a';
        return 0 <= c && c <= 5;
    }

    public static boolean isDoor(int x, int y){
        int c = map[x][y] - 'A';
        return 0 <= c && c <= 5;
    }

    public static boolean isHasKey(int x, int y, int hasKeys){
        int door = map[x][y] - 'A';
        return (hasKeys & (1 << door)) > 0;
    }

}
