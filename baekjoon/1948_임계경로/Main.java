import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        int x, cost;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
    }

    public static int n, start, end, cnt;
    public static ArrayList<Node>[] graph, reverse;
    public static int[] degree, dist;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        reverse = new ArrayList[n+1];
        degree = new int[n+1];
        dist = new int[n+1];
        visited = new boolean[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            degree[a]++;
            graph[b].add(new Node(a, c));
            reverse[a].add(new Node(b, c));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        topology();
        dfs(start);

        System.out.println(dist[start]);
        System.out.println(cnt);
    }

    public static void topology(){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(end);

        while(!q.isEmpty()){
            int now = q.poll();

            for (Node node : graph[now]) {
                int next = node.x;
                int cost = node.cost;

                if(dist[next] < dist[now] + cost){
                    dist[next] = dist[now] + cost;
                }

                if(--degree[next] == 0) q.offer(next);
            }
        }
    }

    public static void dfs(int idx){
        if(visited[idx]) return;
        visited[idx] = true;

        for (Node node : reverse[idx]) {
            int x = node.x;
            int cost = node.cost;

            if(dist[idx] == dist[x] + cost){
                cnt++;
                dfs(x);
            }
        }
    }

}
