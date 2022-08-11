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

    public static class Dist implements Comparable<Dist>{
        int from, to, dist;

        public Dist(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Dist o) {
            return this.dist - o.dist;
        }
    }

    public static int n, m;
    public static int[][] map;

    public static boolean[][] visited;
    public static PriorityQueue<Dist> pq = new PriorityQueue<>();
    public static int[] parent;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n][m];
        int idx = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!visited[i][j] && map[i][j] == 1){
                    bfs(i, j, ++idx);
                }
            }
        }

        calDist();

        parent = new int[idx+1];
        for(int i = 1; i <= idx; i++){
            parent[i] = i;
        }

        int ans = 0;
        int cnt = 0;
        while(!pq.isEmpty()){
            Dist d = pq.poll();
            int from = d.from;
            int to = d.to;
            int dist = d.dist;

            if(find(from) == find(to)) continue;

            union(from, to);
            ans += dist;
            cnt++;
        }

        System.out.println(cnt == idx-1 ? ans : -1);
    }

    public static void calDist(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 0) continue;

                for(int d = 0; d < 4; d++){
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    int dist = 0;

                    while(isRange(nx, ny)){
                        if(map[nx][ny] == map[i][j]) break;
                        if(map[nx][ny] != 0 && map[nx][ny] != map[i][j]){
                            if(dist > 1) pq.offer(new Dist(map[i][j], map[nx][ny], dist));
                            break;
                        }
                        dist++;
                        nx += dx[d];
                        ny += dy[d];
                    }
                }
            }
        }
    }

    public static void bfs(int i, int j, int idx){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(i, j));
        visited[i][j] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            map[x][y] = idx;

            for(int d = 0; d < 4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny] && map[nx][ny] == 1){
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny));
                }
            }
        }
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y) parent[y] = x;
    }
}
