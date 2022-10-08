import java.io.*;
import java.util.*;

public class Solution {

    public static class Pos{
        int x, y;
        int rest, dist;

        public Pos(int x, int y, int rest, int dist) {
            this.x = x;
            this.y = y;
            this.rest = rest;
            this.dist = dist;
        }
    }

    public static int n, k, ans;
    public static int[][] map;
    public static boolean[][] visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            ans = 0;

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            int max = 0;
            map = new int[n][n];
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, map[i][j]);
                }
            }

            visited = new boolean[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(map[i][j] == max){
                        visited[i][j] = true;
                        dfs(new Pos(i, j, 1, 1));
                        visited[i][j] = false;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(Pos pos){
        ans = Math.max(ans, pos.dist);
        
        int x = pos.x;
        int y = pos.y;
        int rest = pos.rest;
        int dist = pos.dist;

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!isRange(nx, ny)) continue;

            if(!visited[nx][ny]){
                if(map[x][y] > map[nx][ny]) {
                    visited[nx][ny] = true;
                    dfs(new Pos(nx, ny, rest, dist+1));
                    visited[nx][ny] = false;
                }
                else{
                    if(rest == 0) continue;

                    if(map[x][y] > map[nx][ny] - k){
                        visited[nx][ny] = true;
                        int tmp = map[nx][ny];
                        map[nx][ny] = map[x][y] - 1;

                        dfs(new Pos(nx, ny, 0, dist+1));

                        map[nx][ny] = tmp;
                        visited[nx][ny] = false;
                    }
                }
            }
        }
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }

}
