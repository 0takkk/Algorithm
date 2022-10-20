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

    public static int n;
    public static int[][] map;

    // 9방향 -> 그대로 있을 수도 있음
    public static int[] dx = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    public static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = 8;
        map = new int[n][n];

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < n; j++){
                map[i][j] = str.charAt(j) == '.' ? 0 : 1;  // 벽이면 1 아니면 0
            }
        }

        System.out.println(bfs());
    }

    public static int bfs(){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(7, 0));

        while(!q.isEmpty()){
            int size = q.size();

            boolean[][] visited = new boolean[n][n];  // 매 초마다 visited 초기화

            while(size-->0) {
                Pos p = q.poll();
                int x = p.x;
                int y = p.y;

                if(x == 0 && y == 7) return 1;  // 도착지면 return 1
                if(map[x][y] == 1) continue;  // 현재 위치가 벽이 되면 패스

                for (int i = 0; i < 9; i++) {  // 9방향
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(!isRange(nx, ny)) continue;

                    if(!visited[nx][ny] && map[nx][ny] == 0){  // 방문안했고, 벽이 아니면 큐에 추가
                        visited[nx][ny] = true;
                        q.offer(new Pos(nx, ny));
                    }
                }
            }

            drop();  // 벽 drop
        }

        return 0;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void drop(){
        for(int j = 0; j < n; j++) map[7][j] = 0;

        for(int i = n-2; i >= 0; i--){
            for(int j = 0; j < n; j++){
                if(map[i][j] == 1){
                    map[i][j] = 0;
                    map[i+1][j] = 1;
                }
            }
        }

    }

}
