import java.io.*;
import java.util.*;

public class Solution {

    public static class Pos{
        int x, y, time;

        public Pos(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static int n, m;
    public static int[][] map;
    public static int[][][] tunnel = {
            {},
            {{0,1}, {0, -1}, {1, 0}, {-1, 0}},
            {{-1, 0}, {1, 0}},
            {{0, -1}, {0, 1}},
            {{-1, 0}, {0, 1}},
            {{1, 0}, {0, 1}},
            {{1, 0}, {0, -1}},
            {{-1, 0}, {0, -1}}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            map = new int[n][m];
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < m; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int ans = bfs(r, c, l);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int bfs(int r, int c, int l){
        boolean[][] visited = new boolean[n][m];
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(r, c, 1));
        visited[r][c] = true;

        int cnt = 0;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int time = p.time;

            cnt++;
            if(time >= l) continue;

            for(int i = 0; i < tunnel[map[x][y]].length; i++){
                int nx = x + tunnel[map[x][y]][i][0];
                int ny = y + tunnel[map[x][y]][i][1];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny] && map[nx][ny] != 0){
                    for(int j = 0; j < tunnel[map[nx][ny]].length; j++){
                        int nnx = nx + tunnel[map[nx][ny]][j][0];
                        int nny = ny + tunnel[map[nx][ny]][j][1];

                        if(nnx == x && nny == y){
                            visited[nx][ny] = true;
                            q.offer(new Pos(nx, ny, time+1));
                            break;
                        }
                    }
                }
            }
        }

        return cnt;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
