package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, dist[], count;
    public static ArrayList<Node>[] graph;

    public static class Node implements Comparable<Node>{
        int x, time;

        public Node(int x, int time) {
            this.x = x;
            this.time = time;
        }

        public int compareTo(Node n){
            return this.time - n.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            count = 0;
            graph = new ArrayList[n+1];
            for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

            for(int i = 0; i < d; i++){
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph[b].add(new Node(a, s));
            }

            dijkstra(c);

            int times = 0;
            for(int i = 1; i <= n; i++){
                if(dist[i] != Integer.MAX_VALUE){
                    times = Math.max(times, dist[i]);
                }
            }

            sb.append(count + " " + times + "\n");
        }

        System.out.println(sb.toString());
    }

    public static void dijkstra(int start){
        dist = new int[n+1];
        for(int i = 1; i <= n; i++) dist[i] = Integer.MAX_VALUE;
        dist[start] = 0;

        boolean[] visited = new boolean[n+1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visited[now.x]) continue;
            visited[now.x] = true;

            for(Node next : graph[now.x]){
                if(!visited[next.x] && dist[next.x] > dist[now.x] + next.time){
                    dist[next.x] = dist[now.x] + next.time;
                    pq.offer(new Node(next.x, dist[next.x]));
                }
            }
        }

        for(int i = 1; i <= n; i++){
            if(visited[i]) count++;
        }

    }

}
