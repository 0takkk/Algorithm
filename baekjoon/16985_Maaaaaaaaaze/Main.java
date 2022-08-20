import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int z, x, y;

        public Pos(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }

    public static int n = 5, ans = Integer.MAX_VALUE;
    public static int[][][] map, maze;
    public static int[] height, rotate;
    public static boolean[] heightVisited;

    public static int[] dz = {0, 0, 0, 0, 1, -1};
    public static int[] dx = {0, 0, 1, -1, 0, 0};
    public static int[] dy = {1, -1, 0, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[n][n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < n; k++){
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        height = new int[n];
        rotate = new int[n];
        heightVisited = new boolean[n];

        setHeight(0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void setHeight(int idx){
        if(idx == n){
            setRotate(0);
            return;
        }

        for(int i = 0; i < n; i++){
            if(!heightVisited[i]){
                heightVisited[i] = true;
                height[idx] = i;

                setHeight(idx+1);

                heightVisited[i] = false;
            }
        }
    }

    public static void setRotate(int idx){
        if(idx == n){
            makeMaze();

            if(maze[0][0][0] == 1 && maze[4][4][4] == 1) {
                ans = Math.min(ans, bfs());
                if (ans == 12) {
                    System.out.println(12);
                    System.exit(0);
                }
            }
            return;
        }

        for(int i = 0; i < 4; i++){
            rotate[idx] = i;
            setRotate(idx+1);
        }
    }

    public static void makeMaze(){
        maze = new int[n][n][n];
        for(int i = 0; i < n; i++){
            maze[i] = makeMaze(i).clone();
        }
    }

    public static int[][] makeMaze(int idx){
        int[][] arr = map[height[idx]];
        int cnt = rotate[idx];
        while(cnt-->0){
            arr = rotating(arr);
        }

        return arr;
    }

    public static int[][] rotating(int[][] arr){
        int[][] copy = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                copy[j][n-1-i] = arr[i][j];
            }
        }
        return copy;
    }

    public static int bfs(){
        boolean[][][] visited = new boolean[n][n][n];
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0, 0));
        visited[0][0][0] = true;

        int time = 0;
        while(!q.isEmpty()){
            int size = q.size();
            if(time >= ans) return Integer.MAX_VALUE;
            for(int i = 0; i < size; i++) {
                Pos p = q.poll();
                int z = p.z;
                int x = p.x;
                int y = p.y;

                if(z == 4 && x == 4 && y == 4) return time;

                for (int j = 0; j < 6; j++){
                    int nz = z + dz[j];
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(!isRange(nz, nx, ny)) continue;

                    if(!visited[nz][nx][ny] && maze[nz][nx][ny] == 1){
                        visited[nz][nx][ny] = true;
                        q.offer(new Pos(nz, nx, ny));
                    }
                }
            }
            time++;
        }

        return Integer.MAX_VALUE;
    }

    public static boolean isRange(int z, int x, int y){
        return z >= 0 && z < n && x >= 0 && x < n && y >= 0 && y < n;
    }

}
