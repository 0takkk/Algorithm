import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            return this.end - o.end;
        }
    }

    public static int n;
    public static ArrayList<Node> nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        nodes = new ArrayList<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            nodes.add(new Node(start, end));
        }

        nodes.sort(Comparator.comparingInt(n -> n.start));

        int ans = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int i = 0; i < n; i++){
            Node node = nodes.get(i);

            while(!pq.isEmpty() && pq.peek().end <= node.start){
                pq.poll();
            }

            pq.offer(node);
            ans = Math.max(ans, pq.size());
        }

        System.out.println(ans);
    }

}
