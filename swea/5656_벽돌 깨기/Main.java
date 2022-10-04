import java.io.*;
import java.util.*;

public class Solution {

    public static class Pos{
        int x, y, range;

        public Pos(int x, int y, int range) {
            this.x = x;
            this.y = y;
            this.range = range;
        }
    }

    public static int n, w, h, ans;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            ans = Integer.MAX_VALUE;

            int[][] map = new int[h][w];
            for(int i = 0; i < h; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < w; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, map);

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int idx, int[][] map){
        if(idx == n){

            int cnt = 0;
            for(int i = 0; i < h; i++){
                for(int j = 0; j < w; j++){
                    if(map[i][j] != 0) cnt++;
                }
            }
            ans = Math.min(ans, cnt);

            return;
        }

        for(int i = 0; i < w; i++){
            dfs(idx+1, destroy(i, map));
        }
    }

    public static int[][] destroy(int col, int[][] map){
        int[][] copy = copyMap(map);

        int row = getTop(col, copy);

        if(row == -1) return copy;

        bfs(new Pos(row, col, copy[row][col]), copy);
        drop(copy);

        return copy;
    }

    public static void drop(int[][] copy){
        for(int j = 0; j < w; j++){
            for(int i = h-2; i >= 0; i--){
                if(copy[i][j] != 0){
                    for(int k = h-1; k > i; k--){
                        if(copy[k][j] == 0){
                            copy[k][j] = copy[i][j];
                            copy[i][j] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void bfs(Pos pos, int[][] copy){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        copy[pos.x][pos.y] = 0;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int range = p.range;

            for(int i = 1; i < range; i++){
                for(int j = 0; j < 4; j++){
                    int nx = x + dx[j] * i;
                    int ny = y + dy[j] * i;

                    if(!isRange(nx, ny)) continue;
                    if(copy[nx][ny] == 0) continue;

                    if(copy[nx][ny] > 1) q.offer(new Pos(nx, ny, copy[nx][ny]));
                    copy[nx][ny] = 0;
                }
            }
        }
    }

    public static int getTop(int col, int[][] copy){
        for(int i = 0; i < h; i++){
            if(copy[i][col] != 0) return i;
        }
        return -1;
    }

    public static int[][] copyMap(int[][] map){
        int[][] copy = new int[h][w];
        for(int i = 0; i < h; i++){
            copy[i] = map[i].clone();
        }
        return copy;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < h && y >= 0 && y < w;
    }

}
