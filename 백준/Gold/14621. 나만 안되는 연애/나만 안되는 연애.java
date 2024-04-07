import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int from, to, cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static int n;
    public static boolean[] isMenSchool;
    public static int[] parnet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        isMenSchool = new boolean[n+1];
        parnet = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            if(st.nextToken().charAt(0) == 'M') isMenSchool[i] = true;
            parnet[i] = i;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        while(m-->0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.offer(new Node(from, to, cost));
        }

        int cnt = 0;
        int ans = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int from = node.from;
            int to = node.to;
            int cost = node.cost;

            if(isDifferentSchool(from, to) && (find(from) != find(to))) {
                union(from, to);
                ans += cost;
                cnt++;
            }

            if(cnt == n-1) break;
        }
        
        if(cnt != n-1) {
            System.out.println(-1);
            return;
        }

        System.out.println(ans);
    }

    public static int find(int x) {
        if(x == parnet[x]) return x;
        return parnet[x] = find(parnet[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        parnet[y] = x;
    }

    public static boolean isDifferentSchool(int from, int to) {
        return (isMenSchool[from] && !isMenSchool[to]) || (!isMenSchool[from] && isMenSchool[to]);
    }
}