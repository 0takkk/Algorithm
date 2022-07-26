import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int x;
        long cost;

        public Node(int x, long cost) {
            this.x = x;
            this.cost = cost;
        }


        @Override
        public int compareTo(Node o) {
            if(this.cost > o.cost) return 1;
            else if(this.cost == o.cost) return 0;
            else return -1;
        }
    }

    public static final long MAX = 10000000000L;
    public static int n;
    public static ArrayList<Node>[] graph;
    public static long[][] dist;
    public static int[][] route;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
        }

        dist = new long[n+1][n+1];
        route = new int [n+1][n+1];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                dist[i][j] = MAX;
            }
        }

        for(int i = 1; i <= n; i++){
            dijkstra(i);
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(dist[i][j] == MAX) sb.append("0 ");
                else sb.append(dist[i][j] + " ");
            }
            sb.append("\n");
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(route[i][j] == 0){
                    sb.append("0\n");
                }else{
                    Stack<Integer> stack = new Stack<>();
                    int pre = j;
                    stack.push(j);

                    while(i != route[i][pre]){
                        pre = route[i][pre];
                        stack.push(pre);
                    }

                    sb.append(stack.size()+1 + " ");
                    sb.append(i + " ");
                    while(!stack.isEmpty()){
                        sb.append(stack.pop() + " ");
                    }
                    sb.append("\n");
                }
            }
        }

        System.out.println(sb.toString());
    }

    public static void dijkstra(int start){
        dist[start][start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(dist[start][now.x] < now.cost) continue;

            for (Node next : graph[now.x]) {
                if(dist[start][next.x] > dist[start][now.x] + next.cost){
                    dist[start][next.x] = dist[start][now.x] + next.cost;

                    route[start][next.x] = now.x;

                    pq.offer(new Node(next.x, dist[start][next.x]));
                }
            }
        }
    }

}
