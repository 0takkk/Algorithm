import java.io.*;
import java.util.*;

public class Main {

    public static class Node {
        int x;
        long cost;

        public Node(int x, long cost) {
            this.x = x;
            this.cost = cost;
        }
    }

    public static int n;
    public static ArrayList<Node>[] graph;
    public static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        while(m-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
        }

        dist = new long[n+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        boolean flag = bellmanFord();

        if(!flag) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= n; i++) {
            sb.append(dist[i] == Long.MAX_VALUE ? -1 : dist[i]).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static boolean bellmanFord() {
        for(int i = 1; i <= n; i++) {
            for(int now = 1; now <= n; now++) {
                if(dist[now] == Long.MAX_VALUE) continue;

                for (Node next : graph[now]) {
                    if(dist[next.x] > dist[now] + next.cost) {
                        dist[next.x] = dist[now] + next.cost;
                        if(i == n) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

}