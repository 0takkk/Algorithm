import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;
        char color;

        public Pos(int x, int y, char color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    public static int n;
    public static char[][] arr;
    public static boolean[][] visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        arr = new char[n][n];
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < n; j++){
                arr[i][j] = str.charAt(j);
            }
        }

        int nCnt = 0, nnCnt = 0;
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]){
                    nCnt += normal(new Pos(i, j, arr[i][j]));
                }
            }
        }

        visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]){
                    nnCnt += notNormal(new Pos(i, j, arr[i][j]));
                }
            }
        }

        System.out.println(nCnt + " " + nnCnt);
    }

    public static int normal(Pos pos){
        Queue<Pos> q = new LinkedList<>();
        q.offer(pos);
        visited[pos.x][pos.y] = true;

        while(!q.isEmpty()){
            Pos now = q.poll();
            int x = now.x;
            int y = now.y;
            char color = now.color;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny] && arr[nx][ny] == color){
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny, color));
                }
            }
        }

        return 1;
    }

    public static int notNormal(Pos pos){
        Queue<Pos> q = new LinkedList<>();
        q.offer(pos);
        visited[pos.x][pos.y] = true;

        while(!q.isEmpty()){
            Pos now = q.poll();
            int x = now.x;
            int y = now.y;
            char color = now.color;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny]){
                    if((color == 'R' || color == 'G') && (arr[nx][ny] == 'R' || arr[nx][ny] == 'G')){
                        visited[nx][ny] = true;
                        q.offer(new Pos(nx, ny, color));
                    }
                    else if(color == 'B' && arr[nx][ny] == 'B'){
                        visited[nx][ny] = true;
                        q.offer(new Pos(nx, ny, color));
                    }
                }
            }
        }

        return 1;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }

}
