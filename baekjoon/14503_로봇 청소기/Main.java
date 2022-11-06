import java.io.*;
import java.util.*;

public class Main {

    public static class Robot{
        int x, y, dir;

        public Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

    }

    public static int n, m, ans;
    public static int[][] map;
    public static Robot robot;
    public static boolean[][] visited;

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        robot = new Robot(x, y, dir);

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n][m];

        start();
        System.out.println(ans);

    }

    public static void start(){
        while(true){
            cleaning();
            if(!checkingNext()) return;
        }
    }

    public static void cleaning(){
        visited[robot.x][robot.y] = true;
        ans++;
    }

    public static boolean checkingNext(){
        while(true){
            if(checkingAround()) return true;
            if(!goBack()) return false;
        }
    }

    private static boolean checkingAround() {
        for(int i = 1; i <= 4; i++){
            int nx = robot.x + dx[(robot.dir - i + 4) % 4];
            int ny = robot.y + dy[(robot.dir - i + 4) % 4];

            if(!isRange(nx, ny)) continue;

            robot.x = nx;
            robot.y = ny;
            robot.dir = (robot.dir - i + 4) % 4;
            return true;
        }
        return false;
    }

    public static boolean goBack(){
        robot.x = robot.x - dx[robot.dir];
        robot.y = robot.y - dy[robot.dir];
        if(map[robot.x][robot.y] == 1) return false;
        return true;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m && !visited[x][y] && map[x][y] == 0;
    }

}
