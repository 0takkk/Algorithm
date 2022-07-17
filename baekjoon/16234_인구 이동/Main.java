import java.io.*;
import java.util.*;

public class Main {

    public static int n, l, r;
    public static int[][] map;
    public static boolean[][] visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        while(true){
            visited = new boolean[n][n];
            boolean flag = false;

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(!visited[i][j]){
                        if(bfs(i, j)){
                            flag = true;
                        }
                    }
                }
            }

            if(!flag) break;
            ans++;
        }

        System.out.println(ans);
    }

    public static boolean bfs(int i, int j){
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(i, j));
        visited[i][j] = true;

        ArrayList<Pos> list = new ArrayList<>();
        list.add(new Pos(i, j));
        int sum = map[i][j];

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            for(int d = 0; d < 4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(!isRange(nx, ny)) continue;

                int diff = Math.abs(map[x][y] - map[nx][ny]);

                if((l <= diff && diff <= r) && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    sum += map[nx][ny];
                    list.add(new Pos(nx, ny));
                    q.offer(new Pos(nx, ny));
                }
            }
        }

        if(list.size() == 1) return false;

        int avg = sum / list.size();
        for (Pos p : list) {
            map[p.x][p.y] = avg;
        }

        return true;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }

}
