package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, k, result;
    public static ArrayList<Node>[] graph;
    public static PriorityQueue<Node> pq;
    public static boolean[] visited;

    public static class Node implements Comparable<Node>{
        int x, weight;

        public Node(int x, int weight) {
            this.x = x;
            this.weight = weight;
        }

        public int compareTo(Node n){
            return this.weight - n.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[] station = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++) station[i] = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        visited = new boolean[n+1];
        pq = new PriorityQueue<>();
        for (int s : station) {
            visited[s] = true;
            for(Node next : graph[s]){
                pq.offer(next);
            }
        }

        dijkstra();

        System.out.println(result);
    }

    public static void dijkstra(){
        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visited[now.x]) continue;
            visited[now.x] = true;

            result += now.weight;

            for(Node next : graph[now.x]){
                pq.offer(next);
            }
        }
    }

}
