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

    public static int n, k;
    public static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[2][n+k+1];
        for(int i = 0; i < 2; i++){
            Arrays.fill(map[i], 1);
        }

        for(int i = 0; i < 2; i++){
            String str = br.readLine();
            for(int j = 0; j < n; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        if(bfs()) System.out.println(1);
        else System.out.println(0);
    }

    public static boolean bfs(){
        boolean[][] visited = new boolean[2][n+k+1];
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0));
        visited[0][0] = true;

        int idx = 0;

        while(!q.isEmpty()){
            int size = q.size();
            while(size-->0) {
                Pos p = q.poll();
                int x = p.x;
                int y = p.y;

                if (map[x][y] == 0) continue;
                if (y > n) return true;

                int nx, ny;
                for (int i = 0; i < 3; i++) {
                    if (i == 0) {
                        nx = x;
                        ny = y + 1;
                    } else if (i == 1) {
                        nx = x;
                        ny = y - 1;
                    } else {
                        nx = x == 0 ? 1 : 0;
                        ny = y + k;
                    }

                    if (nx < 0 || nx > 1 || ny < 0 || ny > n + k) continue;

                    if (!visited[nx][ny] && map[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        q.offer(new Pos(nx, ny));
                    }
                }
            }

            map[0][idx] = map[1][idx] = 0;
            idx++;
        }

        return false;
    }

}
