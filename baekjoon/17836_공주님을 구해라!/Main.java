import java.io.*;
import java.util.*;

public class Main {

    public static class Pos implements Comparable<Pos>{
        int x, y;
        int gram, move;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int gram, int move) {
            this.x = x;
            this.y = y;
            this.gram = gram;
            this.move = move;
        }

        @Override
        public int compareTo(Pos o) {
            return this.move - o.move;
        }
    }

    public static int n, m, t;
    public static int[][] map;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int move = bfs();

        if(move == -1) System.out.println("Fail");
        else System.out.println(move);
    }

    public static int bfs(){
        boolean[][][] visited = new boolean[2][n][m];
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0, 0, 0));
        visited[0][0][0] = true;

        while(!pq.isEmpty()){
            Pos p = pq.poll();
            int x = p.x;
            int y = p.y;
            int gram = p.gram;
            int move = p.move;

            if(x == n-1 && y == m-1) return move;
            if(move > t) return -1;
            if(map[x][y] == 2) gram = 1;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx ,ny)) continue;

                if(gram == 0 && map[nx][ny] != 1 && !visited[gram][nx][ny]){
                    visited[gram][nx][ny] = true;
                    pq.offer(new Pos(nx, ny, gram, move+1));
                }
                else if(gram == 1 && !visited[gram][nx][ny]){
                    visited[gram][nx][ny] = true;
                    pq.offer(new Pos(nx, ny, gram, move+1));
                }
            }

        }

        return -1;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
