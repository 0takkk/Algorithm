import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int x;
        int cost;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static int n;
    public static ArrayList<Node>[] graph;
    public static int count, time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n+1];
            for(int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            while(d-->0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph[b].add(new Node(a, cost));
            }

            dijkstra(c);

            sb.append(count).append(" ").append(time).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dijkstra(int c) {
        int[] costs = new int[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(c, 0));
        Arrays.fill(costs, 987654321);
        costs[c] = 0;
        count = 0;
        time = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(costs[now.x] < now.cost) continue;
            count++;
            time = Math.max(time, costs[now.x]);

            for (Node next : graph[now.x]) {
                if(costs[next.x] > costs[now.x] + next.cost) {
                    costs[next.x] = costs[now.x] + next.cost;
                    pq.offer(new Node(next.x, costs[next.x]));
                }
            }
        }
    }

}
