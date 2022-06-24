import java.io.*;
import java.util.*;

public class Main {

    public static final int MAX = 100000;
    public static class Node implements Comparable<Node>{
        int x;
        long value;

        public Node(int x, long value) {
            this.x = x;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return o.x - this.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] list = new ArrayList[MAX+1];
        PriorityQueue<Node> q = new PriorityQueue<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            long v = Long.parseLong(st.nextToken());

            if(list[y] == null) list[y] = new ArrayList<>();

            list[y].add(new Node(x, v));
        }

        long sum = 0;
        long max = 0;

        for(int y = 0; y <= MAX; y++){
            if(list[y] == null) continue;

            for (Node node : list[y]) {
                q.add(node);
                sum += node.value;
            }

            int preX = 0;

            while(!q.isEmpty() && q.size() > c){
                preX = q.peek().x;

                while(!q.isEmpty() && q.peek().x == preX){
                    sum -= q.poll().value;
                }
            }

            max = Math.max(max, sum);
        }

        System.out.println(max);
    }

}
