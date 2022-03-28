import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int x, y;
        double cost;

        public Node(int x, int y, double cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            if(this.cost == o.cost) return 0;
            else if(this.cost > o.cost) return 1;
            else return -1;
        }
    }

    public static PriorityQueue<Node> pq = new PriorityQueue<>();
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Double[][] arr = new Double[n][2];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Double.parseDouble(st.nextToken());
            arr[i][1] = Double.parseDouble(st.nextToken());
        }

        for(int i = 0; i < n-1; i++){
            for(int j = i+1; j < n; j++){
                double cost = Math.sqrt(Math.pow((arr[i][0] - arr[j][0]), 2) + Math.pow((arr[i][1] - arr[j][1]), 2));
                pq.offer(new Node(i, j, cost));
                pq.offer(new Node(j, i, cost));
            }
        }

        parent = new int[n];
        for(int i = 0; i < n; i++)
            parent[i] = i;


        double cost = mst();
        System.out.println(cost);
    }

    public static double mst(){
        double result = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int x = node.x;
            int y = node.y;

            if(find(x) != find(y)){
                union(x, y);
                result += node.cost;
            }
        }

        return result;
    }

    public static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y)
            parent[y] = x;
    }

}
