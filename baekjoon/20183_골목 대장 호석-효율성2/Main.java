import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int x;
        long cost, shy;

        public Node(int x, long cost) {
            this.x = x;
            this.cost = cost;
        }

        public Node(int x, long cost, long shy) {
            this.x = x;
            this.cost = cost;
            this.shy = shy;
        }

        @Override
        public int compareTo(Node o) {
            if(this.shy == o.shy) {
                if(this.cost > o.cost) return 1;
                else if(this.cost == o.cost) return 0;
                else return -1;
            }
            else{
                if(this.shy > o.shy) return 1;
                else return -1;
            }
        }
    }

    public static int n;
    public static long c, MAX = 50000000000000001L;
    public static ArrayList<Node>[] list;
    public static long[] shame;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        c = Long.parseLong(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            list[s].add(new Node(e, cost));
            list[e].add(new Node(s, cost));
        }

        shame = new long[n+1];
        Arrays.fill(shame, MAX);

        System.out.println(dijkstra(a, b));
    }

    public static long dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(shame[now.x] <= now.shy) continue;
            if(now.x == end) return now.shy;

            shame[now.x] = now.shy;

            for(Node next : list[now.x]){
                long newDist = now.cost + next.cost;
                long maxShame = Math.max(now.shy, next.cost);

                if(newDist > c || shame[next.x] <= maxShame) continue;

                pq.offer(new Node(next.x, newDist, maxShame));
            }
        }

        return -1;
    }

}
