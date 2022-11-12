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

    public static int size, total, max;
    public static int[][] map;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        size = (int) Math.pow(2, n);
        map = new int[size][size];

        for(int i = 0; i < size; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < size; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while(q-->0){
            int l = Integer.parseInt(st.nextToken());
            rotate(l);
            melt();
        }

        bfs();

        System.out.println(total);
        System.out.println(max);
    }

    public static void bfs(){
        Queue<Pos> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                total += map[i][j];

                if(map[i][j] > 0 && !visited[i][j]){
                    q.add(new Pos(i, j));
                    visited[i][j] = true;
                    int cnt = 1;

                    while(!q.isEmpty()){
                        Pos p = q.poll();
                        int x = p.x;
                        int y = p.y;

                        for(int d = 0; d < 4; d++){
                            int nx = x + dx[d];
                            int ny = y + dy[d];

                            if(!isRange(nx, ny)) continue;

                            if(map[nx][ny] > 0 && !visited[nx][ny]){
                                visited[nx][ny] = true;
                                q.offer(new Pos(nx, ny));
                                cnt++;
                            }
                        }
                    }
                    max = Math.max(max, cnt);
                }
            }
        }
    }

    public static void melt(){
        int[][] tmp = new int[size][size];
        for(int i = 0; i < size; i++) tmp[i] = map[i].clone();

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(map[i][j] == 0) continue;

                int cnt = 0;
                for(int d = 0; d < 4; d++){
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(!isRange(nx, ny)) continue;

                    if(map[nx][ny] > 0) cnt++;
                }

                if(cnt < 3) tmp[i][j]--;
            }
        }

        map = tmp;
    }

    public static void rotate(int l){
        int rotateSize = (int)Math.pow(2, l);
        int[][] tmp = new int[size][size];

        for(int i = 0; i < size; i += rotateSize){
            for(int j = 0; j < size; j += rotateSize){
                rotate(i, j, rotateSize, tmp);
            }
        }

        map = tmp;
    }

    public static void rotate(int x, int y, int rotateSize, int[][] tmp){
        for(int i = 0; i < rotateSize; i++){
            for(int j = 0; j < rotateSize; j++){
                tmp[x+i][y+j] = map[x+rotateSize-1-j][y+i];
            }
        }
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < size && y >= 0 && y < size;
    }
}
