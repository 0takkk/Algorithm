import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;

    public static class Node implements Comparable<Node>{
        int from, to;

        public Node(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Node o) {
            return this.from - o.from;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "from=" + from +
                    ", to=" + to +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        PriorityQueue<Node> back = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if(from > to) back.offer(new Node(to, from));
        }

        long ans = 0L;
        int r = 0;
        int l = 0;

        while(!back.isEmpty()){
            Node node = back.poll();

            if(node.from > r){
                ans += (r - l);
                l = node.from;
                r = node.to;
            }
            else{
                r = Math.max(r, node.to);
            }
        }

        ans += r - l;
        System.out.println(m + 2 * ans);
    }

}
