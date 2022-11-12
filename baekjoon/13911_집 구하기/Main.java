import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int x, cost;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static final int INF = 987654321;
    public static int v, x, y;
    public static ArrayList<Node>[] graph;
    public static HashSet<Integer> mcdonalds, starbucks;
    public static int[] mcDist, starDist;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        mcDist = new int[v+1];
        starDist = new int[v+1];

        graph = new ArrayList[v+1];
        for(int i = 1; i <= v; i++){
            graph[i] = new ArrayList<>();
            mcDist[i] = starDist[i] = INF;
        }

        while(e-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, cost));
            graph[b].add(new Node(a, cost));
        }

        mcdonalds = new HashSet<>();
        starbucks = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        while(m-->0){
            mcdonalds.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        while(s-->0){
            starbucks.add(Integer.parseInt(st.nextToken()));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited = new boolean[v+1];

        // 맥도날드 거리 구하기
        for (int mcdonald : mcdonalds) {
            if(visited[mcdonald]) continue;
            visited[mcdonald] = true;

            pq.offer(new Node(mcdonald, 0));
            mcDist[mcdonald] = 0;

            while(!pq.isEmpty()){
                Node now = pq.poll();

                for (Node next : graph[now.x]) {
                    if(mcdonalds.contains(next.x) && !visited[next.x]){
                        visited[next.x] = true;
                        mcDist[next.x] = 0;
                        pq.add(new Node(next.x, 0));
                    }
                    else{
                        if(now.cost + next.cost > x) continue;
                        if(mcDist[next.x] < now.cost + next.cost) continue;
                        mcDist[next.x] = now.cost + next.cost;
                        pq.add(new Node(next.x, now.cost + next.cost));
                    }
                }
            }
        }

        // 스타벅스 거리 구하기
        pq.clear();
        for (int starbuck : starbucks) {
            if(visited[starbuck]) continue;
            visited[starbuck] = true;

            pq.add(new Node(starbuck, 0));
            starDist[starbuck] = 0;

            while(!pq.isEmpty()){
                Node now = pq.poll();
                int x = now.x;
                int cost = now.cost;

                for(Node next : graph[x]){
                    if(starbucks.contains(next.x) && !visited[next.x]){
                        visited[next.x] = true;
                        starDist[next.x] = 0;
                        pq.add(new Node(next.x, 0));
                    }
                    else{
                        if(cost + next.cost > y) continue;
                        if(starDist[next.x] < cost + next.cost) continue;
                        starDist[next.x] = cost + next.cost;
                        pq.add(new Node(next.x, cost + next.cost));
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;

        for(int i = 1; i <= v; i++){
            if(mcdonalds.contains(i) || starbucks.contains(i)) continue;
            if(mcDist[i] == INF || starDist[i] == INF) continue;

            ans = Math.min(ans, mcDist[i] + starDist[i]);
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

}
