import java.io.*;
import java.util.*;

public class Main {

    public static int n, result;
    public static int well[], map[][], parent[];
    public static PriorityQueue<Node> pq;

    public static class Node implements Comparable<Node>{
        int x, y, weight;

        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        well = new int[n+1];
        map = new int[n+1][n+1];
        parent = new int[n+1];
        pq = new PriorityQueue<>();

        for(int i = 1; i <= n; i++){
            well[i] = Integer.parseInt(br.readLine());
            parent[i] = i;
        }

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(i == j) pq.offer(new Node(0, i, well[i]));
                else if(i < j) pq.offer(new Node(i, j, map[i][j]));
            }
        }

        mst();

        System.out.println(result);
    }

    public static void mst(){
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int x = node.x;
            int y = node.y;

            if(find(x) == find(y)) continue;

            union(x, y);
            result += node.weight;
        }
    }


    public static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        parent[x] = y;
    }

}
