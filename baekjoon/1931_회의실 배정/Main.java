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
            if(this.end == o.end) return this.start - o.start;
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue<>();

        while(n-->0){
            st = new StringTokenizer(br.readLine());
            pq.offer(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int count = 0;
        int time = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int start = node.start;
            int end = node.end;

            if(start >= time){
                count++;
                time = end;
            }
        }

        System.out.println(count);
    }

}
