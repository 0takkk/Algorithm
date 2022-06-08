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

        public int compareTo(Node n){
            if(this.shy - n.shy > 0) return 1;
            else if(this.shy - n.shy < 0) return -1;
            return 0;
        }
    }

    public static int n, m, a, b;
    public static long c;
    public static ArrayList<Node>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Long.parseLong(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list[from].add(new Node(to, cost));
            list[to].add(new Node(from, cost));
        }

        System.out.println(dijkstra());
    }

    public static long dijkstra(){
        long[] arr = new long[n+1];
        Arrays.fill(arr, Long.MAX_VALUE);
        arr[a] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(a, 0, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int x = now.x;
            long cost = now.cost;
            long shy = now.shy;

            if(x == b) return shy;
            if(arr[x] < shy) continue;

            for(Node next : list[x]){
                long newCost = cost + next.cost;
                long newShy = Math.max(shy, next.cost);

                if(newCost <= c && arr[next.x] > newShy){
                    arr[next.x] = newShy;
                    pq.offer(new Node(next.x, newCost, newShy));
                }
            }
        }
        return -1;
    }

}
