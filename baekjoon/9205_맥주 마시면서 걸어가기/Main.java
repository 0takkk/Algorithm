import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;
        boolean isStore;

        public Pos(int x, int y, boolean isStore) {
            this.x = x;
            this.y = y;
            this.isStore = isStore;
        }
    }

    public static class Edge{
        int idx, rest;

        public Edge(int idx, int rest) {
            this.idx = idx;
            this.rest = rest;
        }
    }

    public static int n;
    public static Pos[] pos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while(t-->0){
            n = Integer.parseInt(br.readLine());
            pos = new Pos[n+2];

            for(int i = 0; i < n+2; i++){
                st = new StringTokenizer(br.readLine());
                boolean isStore = i >= 1 && i <= n;
                pos[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), isStore);
            }

            sb.append(bfs() ? "happy" : "sad").append("\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean bfs(){
        boolean[] visited = new boolean[n+2];
        Queue<Edge> q = new ArrayDeque<>();
        q.offer(new Edge(0, 1000));
        visited[0] = true;

        while(!q.isEmpty()){
            Edge e = q.poll();
            Pos now = pos[e.idx];
            if(now.isStore) e.rest = 1000;

            if(e.idx == n+1) return true;

            for(int i = 0; i < n+2; i++){
                if(visited[i]) continue;

                Pos next = pos[i];
                int dist = Math.abs(now.x - next.x) + Math.abs(now.y - next.y);

                if(dist <= e.rest){
                    visited[i] = true;
                    q.offer(new Edge(i, e.rest - dist));
                }
            }
        }

        return false;
    }

}
