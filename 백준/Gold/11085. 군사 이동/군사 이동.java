import java.io.*;
import java.util.*;

public class Main {

    public static int p, c, v;
    public static int[] parent;
    public static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static class Node implements Comparable<Node>{
        int start, end, width;

        public Node(int start, int end, int width) {
            this.start = start;
            this.end = end;
            this.width = width;
        }

        @Override
        public int compareTo(Node o) {
            return o.width - this.width;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        parent = new int[p];
        for(int i = 0; i < p; i++)
            parent[i] = i;

        for(int i = 0; i < w; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.offer(new Node(a, b, c));
        }

        System.out.println(mst());
    }

    public static int mst(){
        int result = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int x = node.start;
            int y = node.end;
            int w = node.width;

            if(find(x) == find(y)) continue;

            union(x, y);
            if(find(c) == find(v)){
                result = w;
                break;
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

        if(x < y){
            int tmp = x;
            x = y;
            y = tmp;
        }

        parent[y] = x;
    }

}