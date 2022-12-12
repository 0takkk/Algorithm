import java.io.*;
import java.util.*;

public class Main {

    public static class Pos implements Comparable<Pos>{
        int x, y, move;

        public Pos(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "x=" + x +
                    ", y=" + y +
                    ", move=" + move +
                    '}';
        }

        @Override
        public int compareTo(Pos o) {
            return this.move - o.move;
        }
    }

    public static int n, m;
    public static char map[][];
    public static PriorityQueue<Pos> pq;
    public static Pos[] keys;
    public static boolean[][] visited;
    public static int[][] time;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][n];
        pq = new PriorityQueue<>();
        keys = new Pos[m];
        visited = new boolean[n][n];
        time = new int[n][n];

        int keyIdx = 0;

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            Arrays.fill(time[i], Integer.MAX_VALUE);

            for(int j = 0; j < n; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S'){
                    pq.offer(new Pos(i, j, 0));
                    pq.offer(new Pos(i, j, 0));
                    visited[i][j] = true;
                }
                if(map[i][j] == 'K'){
                    keys[keyIdx++] = new Pos(i, j, 0);
                }
            }
        }

        System.out.println(bfs());
    }

    public static int bfs(){
        int count = 0;

        while(!pq.isEmpty()){
            Pos p = pq.poll();
            int x = p.x;
            int y = p.y;
            int move = p.move;

            if(!visited[x][y] && map[x][y] == 'K'){
                visited[x][y] = true;
                time[x][y] = move;
                count++;

                if(count == m){
                    int ans = 0;
                    for(int i = 0; i < m; i++){
                        Pos key = keys[i];
                        ans += time[key.x][key.y];
                    }

                    return ans;
                }

                pq.offer(new Pos(x, y, 0));
                continue;
            }

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny) || map[nx][ny] == '1') continue;

                if(map[nx][ny] == 'K'){
                    if(!visited[nx][ny]) pq.offer(new Pos(nx, ny, move+1));
                }
                else if(time[nx][ny] > move+1){
                    time[nx][ny] = move+1;
                    pq.offer(new Pos(nx, ny, move+1));
                }
            }
        }

        return -1;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
