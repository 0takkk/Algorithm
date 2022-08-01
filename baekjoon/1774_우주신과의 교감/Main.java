import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static Pos[] pos;
    public static int[] parent;

    public static class Pos implements Comparable<Pos>{
        int from, to;
        int x, y;
        double dist;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        public int compareTo(Pos p){
            if(this.dist - p.dist > 0) return 1;
            else if(this.dist == p.dist) return 0;
            else return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        pos = new Pos[n+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pos[i] = new Pos(x, y);
            parent[i] = i;
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        PriorityQueue<Pos> pq = new PriorityQueue<>();

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i-1; j++){
                double dist = Math.sqrt(Math.pow(pos[i].x - pos[j].x, 2) + Math.pow(pos[i].y - pos[j].y, 2));
                pq.offer(new Pos(i, j, dist));
            }
        }

        double ans = 0;
        while(!pq.isEmpty()){
            Pos p = pq.poll();
            int from = p.from;
            int to = p.to;
            double dist = p.dist;

            if(find(from) == find(to)) continue;

            union(from, to);
            ans += dist;
        }

        System.out.printf("%.2f", ans);
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
