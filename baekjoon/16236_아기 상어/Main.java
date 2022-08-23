import java.io.*;
import java.util.*;

public class Main {

    public static class Pos implements Comparable<Pos>{
        int x, y;
        int dist;

        public Pos(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pos o) {
            if(this.dist == o.dist){
                if(this.x == o.x) return this.y - o.y;
                return this.x - o.x;
            }
            return this.dist - o.dist;
        }
    }

    public static class Shark{
        int x, y, size, eat;

        public Shark(int x, int y, int size, int eat) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.eat = eat;
        }
    }

    public static int n, fish, ans;
    public static int[][] map;
    public static Shark shark;
    public static Pos target;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    shark = new Shark(i, j, 2, 0);
                    map[i][j] = 0;
                }
                else if(map[i][j] != 0) fish++;
            }
        }

        while(true){
            if(!findWillEatFish()) break;
            eatFish();
        }

        System.out.println(ans);
    }

    public static boolean findWillEatFish(){
        if(fish == 0) return false;

        boolean[][] visited = new boolean[n][n];
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(shark.x, shark.y, 0));
        visited[shark.x][shark.y] = true;

        while(!pq.isEmpty()){
            Pos p = pq.poll();
            int x = p.x;
            int y = p.y;
            int dist = p.dist;

            if(map[x][y] != 0 && map[x][y] < shark.size){
                target = new Pos(x, y, dist);
                return true;
            }

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny] && map[nx][ny] <= shark.size){
                    visited[nx][ny] = true;
                    pq.offer(new Pos(nx, ny, dist+1));
                }
            }
        }

        return false;
    }

    public static void eatFish(){
        ans += target.dist;
        shark.x = target.x;
        shark.y = target.y;

        if(++shark.eat == shark.size){
            shark.eat = 0;
            shark.size++;
        }

        map[target.x][target.y] = 0;
        fish--;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }

}
