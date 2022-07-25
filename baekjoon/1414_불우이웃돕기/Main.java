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

    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        parent = new int[n+1];

        int sum = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int i = 1; i <= n; i++){
            String str = br.readLine();
            parent[i] = i;

            for(int j = 1; j <= n; j++){
                char c = str.charAt(j-1);
                int num = 1;
                if(c == '0') continue;

                if('a' <= c && c <= 'z') num += c - 'a';
                else num += c - 'A' + 26;

                if(i != j){
                   pq.offer(new Node(i, j, num));
                }
                sum += num;
            }
        }

        int cnt = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int from = node.from;
            int to = node.to;
            int cost = node.cost;

            if(find(from) == find(to)) continue;

            union(from, to);
            cnt++;
            sum -= cost;
        }

        if(cnt == n-1) System.out.println(sum);
        else System.out.println(-1);
    }

    public static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y) parent[y] = x;
    }

}
