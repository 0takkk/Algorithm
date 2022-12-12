import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y, move;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    public static class Edge implements Comparable<Edge>{
        int v1, v2, w;

        public Edge(int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    public static int n, m;
    public static char map[][];
    public static Pos[] keys;
    public static int[] parent;
    public static PriorityQueue<Edge> pq;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][n];
        parent = new int[m+1];
        keys = new Pos[m+1];
        pq = new PriorityQueue<>();

        for(int i = 1; i <= m; i++) parent[i] = i;

        int keyIdx = 0;

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < n; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] != '1' && map[i][j] != '0'){
                    keys[keyIdx++] = new Pos(i, j);
                }
            }
        }

        for(int i = 0; i <= m; i++) bfs(i);

        int ans = 0;
        int count = 0;
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            int v1 = e.v1;
            int v2 = e.v2;
            int w = e.w;

            if(find(v1) == find(v2)) continue;

            union(v1, v2);
            ans += w;
            count++;

            if(count == m) break;
        }

        if(count != m) ans = -1;

        System.out.println(ans);
    }

    public static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        parent[y] = x;
    }

    public static void bfs(int start){
        boolean[][] visited = new boolean[n][n];
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(keys[start].x, keys[start].y, 0));
        visited[keys[start].x][keys[start].y] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int move = p.move;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx ,ny)) continue;

                if(!visited[nx][ny] && map[nx][ny] != '1'){
                    visited[nx][ny] = true;

                    if(map[nx][ny] != '0'){
                        for(int j = 0; j <= m; j++){
                            if(keys[j].x == nx && keys[j].y == ny){
                                pq.offer(new Edge(start, j, move+1));
                                break;
                            }
                        }
                    }

                    q.offer(new Pos(nx, ny, move+1));
                }
            }
        }
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
