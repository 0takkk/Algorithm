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

    public static int r, c;
    public static char[][] map, copy;
    public static boolean[][] visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        copy = new char[r][c];
        for(int i = 0; i < r; i++){
            String str = br.readLine();
            for(int j = 0; j < c; j++){
                map[i][j] = str.charAt(j);
                copy[i][j] = '.';
            }
        }

        visited = new boolean[r][c];
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(!visited[i][j] && map[i][j] == 'X'){
                    bfs(new Pos(i, j));
                }
            }
        }

        int minX = 11, maxX = -1, minY = 11, maxY = -1;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(copy[i][j] == 'X'){
                    minX = Math.min(minX, i);
                    maxX = Math.max(maxX, i);
                    minY = Math.min(minY, j);
                    maxY = Math.max(maxY, j);
                }
            }
        }

        for(int i = minX; i <= maxX; i++){
            for(int j = minY; j <= maxY; j++){
                System.out.print(copy[i][j]);
            }
            System.out.println();
        }

    }

    public static void bfs(Pos pos){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        visited[pos.x][pos.y] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            int cnt = 0;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(map[nx][ny] == 'X') cnt++;
                if(!visited[nx][ny] && map[nx][ny] == 'X'){
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny));
                }
            }

            if(cnt >= 2) copy[x][y] = 'X';
        }
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < r && y >= 0 && y < c;
    }

}
