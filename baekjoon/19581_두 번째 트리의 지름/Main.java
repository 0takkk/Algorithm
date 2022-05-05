import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Node>[] list;

    public static class Node{
        int x, cost;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        Node n1 = bfs(1, 0);
        Node n2 = bfs(n1.x, 0);

        Node result1 = bfs(n1.x, n2.x);
        Node result2 = bfs(n2.x, n1.x);

        System.out.println(Math.max(result1.cost, result2.cost));
    }

    public static Node bfs(int start, int ext){
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        q.offer(new Node(start, 0));
        Node node = new Node(start, 0);

        while(!q.isEmpty()){
            Node now = q.poll();
            visited[now.x] = true;

            if(now.cost > node.cost && now.x != ext){
                node = now;
            }

            for(Node next : list[now.x]){
                if(!visited[next.x] && next.x != ext){
                    q.offer(new Node(next.x, now.cost + next.cost));
                }
            }
        }

        return node;
    }
}
