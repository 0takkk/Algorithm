import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;
        String str;

        public Pos(int x, int y, String str) {
            this.x = x;
            this.y = y;
            this.str = str;
        }
    }

    public static int n, m;
    public static char[][] map;
    public static boolean[][][] visited;

    public static int[] dx = {0, 1};
    public static int[] dy = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        for(int i = 0; i < n; i++){
            map[i] = br.readLine().toCharArray();
        }

        visited = new boolean[n][m][2];
        String ans = "zzzzzzzzzzzzzzzzzzzz";

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                for(int k = 0; k < 2; k++){
                    if(map[i][j] != '#' && !visited[i][j][k]){
                        String result = bfs(i, j, k);

                        if(result.length() >= 2){
                            ans = ans.compareTo(result) < 0 ? ans : result;
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }

    public static String bfs(int x, int y, int k){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(x, y, map[x][y]+""));
        visited[x][y][k] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();

            int nx = p.x + dx[k];
            int ny = p.y + dy[k];

            if(!isRange(nx, ny) || map[nx][ny] == '#'){
                return p.str;
            }

            visited[nx][ny][k] = true;
            q.offer(new Pos(nx, ny, p.str+map[nx][ny]));
        }

        return "";
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
