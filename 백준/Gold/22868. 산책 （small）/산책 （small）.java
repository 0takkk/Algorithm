import java.io.*;
import java.util.*;

public class Main {

    public static class Node {
        int x, move;
        boolean[] route;

        public Node(int x, int move) {
            this.x = x;
            this.move = move;
        }

        public Node(int x, int move, boolean[] route) {
            this.x = x;
            this.move = move;
            this.route = route;
        }
    }

    public static int n;
    public static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        for(int i = 1; i <= n; i++) {
            Collections.sort(graph[i]);
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        System.out.println(front(s, e));
    }

    public static int front(int s, int e) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        visited[s] = true;
        boolean[] route = new boolean[n+1];
        route[s] = true;
        q.offer(new Node(s, 0, route));

        while(!q.isEmpty()) {
            Node node = q.poll();

            if(node.x == e) {
                return back(s, e, node.route, node.move);
            }

            for (int next : graph[node.x]) {
                if(!visited[next]) {
                    visited[next] = true;
                    boolean[] nextRoute = node.route.clone();
                    nextRoute[next] = true;

                    q.offer(new Node(next, node.move+1, nextRoute));
                }
            }
        }

        return -1;
    }

    public static int back(int s, int e, boolean[] visited, int move) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(e, move));

        while(!q.isEmpty()) {
            Node node = q.poll();

            for (int next : graph[node.x]) {
                if(next == s) {
                    return node.move+1;
                }

                if(!visited[next]) {
                    visited[next] = true;
                    q.offer(new Node(next, node.move+1));
                }
            }
        }

        return -1;
    }

}