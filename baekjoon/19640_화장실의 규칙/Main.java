import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int idx, d, h, lineNum;

        public Node(int idx, int d, int h, int lineNum) {
            this.idx = idx;
            this.d = d;
            this.h = h;
            this.lineNum = lineNum;
        }

        @Override
        public int compareTo(Node o) {
            if(this.d == o.d) {
                if(this.h == o.h) return this.lineNum - o.lineNum;
                return o.h - this.h;
            }
            return o.d - this.d;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Node>[] q = new Queue[m];

        for(int i = 0; i < m; i++){
            q[i] = new ArrayDeque<>();
        }

        int lineNum = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            q[lineNum].offer(new Node(i, d, h, lineNum));
            if(++lineNum == m) lineNum = 0;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int i = 0; i < m; i++){
            if(!q[i].isEmpty()) pq.offer(q[i].poll());
        }

        int ans = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();

            if(node.idx == k) break;

            if(!q[node.lineNum].isEmpty()) pq.offer(q[node.lineNum].poll());
            ans++;
        }

        System.out.println(ans);
    }

}
