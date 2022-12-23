import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int x, cost;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static final int INF = 987654321;
    public static int n, x, y;
    public static ArrayList<Node>[] graph;
    public static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n];
        dist = new int[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        Arrays.fill(dist, INF);

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        dijkstra();

        System.out.println(calDay());
    }

    public static int calDay(){
        Arrays.sort(dist);
        int day = 1;
        int sum = 0;
        for(int i = 1; i < n; i++){
            int shuttleDist = 2 * dist[i];

            if(shuttleDist > x) return -1;

            if(sum + shuttleDist <= x){
                sum += shuttleDist;
            } else{
                day++;
                sum = shuttleDist;
            }
        }

        return day;
    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(y, 0));
        dist[y] = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int x = node.x;
            int cost = node.cost;

            if(dist[x] < cost) continue;

            for (Node next : graph[x]) {
                if(dist[next.x] > dist[x] + next.cost){
                    dist[next.x] = dist[x] + next.cost;
                    pq.offer(new Node(next.x, dist[next.x]));
                }
            }
        }
    }

}
