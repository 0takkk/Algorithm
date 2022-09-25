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

    public static final int INF = 999999999;
    public static int n;
    public static ArrayList<Node>[] graph;
    public static int[] dist;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(tc-->0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            dist = new int[n+1];
            graph = new ArrayList[n+1];
            for(int i = 1; i <= n; i++){
                graph[i] = new ArrayList<>();
            }

            while(m-->0){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph[from].add(new Node(to, cost));
                graph[to].add(new Node(from, cost));
            }

            while(w-->0){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph[from].add(new Node(to, -1 * cost));
            }

            boolean flag = false;
            for(int i = 1; i <= n; i++){
                if(bellmanFord(i)){
                    sb.append("YES\n");
                    flag = true;
                    break;
                }
            }

            if(!flag) sb.append("NO\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean bellmanFord(int start){
        Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean flag = false;

        // 무한 음수 사이클 방지 : n-1번
        for(int i = 1; i < n; i++){
            flag = false;

            for(int j = 1; j <= n; j++){
                for (Node next : graph[j]) {
                    if(dist[j] == INF) continue;

                    if(dist[next.x] > dist[j] + next.cost){
                        dist[next.x] = dist[j] + next.cost;
                        flag = true;
                    }
                }
            }
            if(!flag) break;  // 더 이상 갱신되지 않으면 break;
        }

        // 음수 사이클 유무 판단
        if(flag){
            for(int i = 1; i <= n; i++){
                for(Node next : graph[i]){
                    if(dist[i] == INF) continue;

                    if(dist[next.x] > dist[i] + next.cost){
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
