import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        char ani;
        long count;

        public Node(char ani, long count) {
            this.ani = ani;
            this.count = count;
        }
    }

    public static int n;
    public static ArrayList<Integer>[] graph;
    public static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        nodes = new Node[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        nodes[1] = new Node('x', 0);

        for(int i = 2; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            char ani = st.nextToken().charAt(0);
            long count = Long.parseLong(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(ani, count);
            graph[num].add(i);
        }

        System.out.println(dfs(1));
    }

    public static long dfs(int now){
        long sheep = nodes[now].ani == 'S' ? nodes[now].count : 0L;
        long wolf = nodes[now].ani == 'W' ? nodes[now].count : 0L;

        for (int next : graph[now]) {
            long subSheep = dfs(next);

            sheep = Math.max(subSheep + sheep - wolf, 0L);
            wolf = Math.max(wolf-subSheep, 0L);
        }

        return sheep;
    }

}
