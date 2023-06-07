import java.io.*;
import java.util.*;

public class Main {

    public static class Pos implements Comparable<Pos>{
        int x, y;
        int step, pass;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int step, int pass) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.pass = pass;
        }

        @Override
        public int compareTo(Pos o) {
            if(this.step == o.step) return this.pass - o.pass;
            return this.step - o.step;
        }
    }

    public static int n, m;
    public static char[][] map;
    public static Pos start;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        ArrayList<Pos> garbages = new ArrayList<>();
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S') start = new Pos(i, j);
                if(map[i][j] == 'g') garbages.add(new Pos(i, j));
            }
        }

        for (Pos p : garbages) {
            for(int i = 0; i < 4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(isRange(nx, ny) && map[nx][ny] == '.') map[nx][ny] = 'p';
            }
        }

        Pos result = bfs();

        System.out.println(result.step + " " + result.pass);
    }

    public static Pos bfs(){
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(start);
        visited[start.x][start.y] = true;

        while(!pq.isEmpty()){
            Pos p = pq.poll();
            int x = p.x;
            int y = p.y;
            int step = p.step;
            int pass = p.pass;

            if(map[x][y] == 'F') return p;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny) || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                switch (map[nx][ny]){
                    case 'g':
                        pq.offer(new Pos(nx, ny, step+1, pass));
                        break;
                    case 'p':
                        pq.offer(new Pos(nx, ny, step, pass+1));
                        break;
                    default:
                        pq.offer(new Pos(nx, ny, step, pass));
                }
            }
        }

        return null;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
