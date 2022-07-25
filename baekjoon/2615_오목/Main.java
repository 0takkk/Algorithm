import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y, color;

        public Pos(int x, int y, int color){
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    public static int n, X, Y;
    public static int[][] map;
    public static boolean[][][] visited;

    public static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = 20;
        map = new int[n][n];
        visited = new boolean[8][n][n];

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
                if(map[i][j] != 0){
                    if(check(new Pos(i, j, map[i][j]))){
                        System.out.println(map[i][j]);
                        System.out.println(X + " " + Y);
                        return;
                    }
                }
            }
        }

        System.out.println(0);
    }

    public static boolean check(Pos pos){
        int x = pos.x;
        int y = pos.y;
        int color = pos.color;
        X = x;
        Y = y;

        for(int i = 0; i < 8; i++){
            int cnt = 1;
            visited[i][x][y] = true;
            int nx = x + dx[i];
            int ny = y + dy[i];

            while(isRange(nx, ny)){
                if(map[nx][ny] == color && !visited[i][nx][ny]){
                    visited[i][nx][ny] = true;
                    cnt++;
                    if(Y > ny){
                        X = nx;
                        Y = ny;
                    }
                }else break;

                nx += dx[i];
                ny += dy[i];
            }

            if(cnt == 5) return true;
        }

        return false;
    }

    public static boolean isRange(int x, int y){
        return x >= 1 && x < n && y >= 1 && y < n;
    }

}
