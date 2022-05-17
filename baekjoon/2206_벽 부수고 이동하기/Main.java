import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y, broken;

        public Pos(int x, int y, int broken) {
            this.x = x;
            this.y = y;
            this.broken = broken;
        }
    }

    public static int n,m;
    public static int[][] map;
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];

        for(int i = 1; i <= n; i++){
            String str = br.readLine();
            for(int j = 1; j <= m; j++){
                map[i][j] = str.charAt(j-1)-'0';
            }
        }

        System.out.println(bfs(new Pos(1, 1, 0)));
    }

    public static int bfs(Pos pos){
        Queue<Pos> q = new LinkedList<>();
        q.offer(pos);
        boolean[][][] visited = new boolean[2][n+1][m+1];
        visited[0][pos.x][pos.y] = true;
        int time = 1;

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                Pos now = q.poll();
                int x = now.x;
                int y = now.y;
                int broken = now.broken;

                if(x == n && y == m){
                    return time;
                }

                for(int j = 0; j < 4; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(!isRange(nx, ny)) continue;

                    if(map[nx][ny] == 1 && broken == 0 && !visited[1][nx][ny]){
                        visited[1][nx][ny] = true;
                        q.offer(new Pos(nx, ny, 1));
                    }
                    if(map[nx][ny] == 0 && !visited[broken][nx][ny]){
                        visited[broken][nx][ny] = true;
                        q.offer(new Pos(nx, ny, broken));
                    }
                }
            }
            time++;
        }

        return -1;
    }

    public static boolean isRange(int x, int y){
        return x > 0 && x <= n && y > 0 && y <= m;
    }

}
