package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, dist[];
    public static ArrayList<Node>[] graph;
    public static ArrayList<Integer> possibleDest;

    public static class Node implements Comparable<Node>{
        int x, weight;

        public Node(int x, int cost) {
            this.x = x;
            this.weight = cost;
        }

        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n+1];
            for(int i = 1; i <= n; i++)
                graph[i] = new ArrayList<>();

            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                if((a == g && b == h) || (a == h && b == g)){
                    graph[a].add(new Node(b, cost * 2 - 1));
                    graph[b].add(new Node(a, cost * 2 - 1));
                }
                else{
                    graph[a].add(new Node(b, cost * 2));
                    graph[b].add(new Node(a, cost * 2));
                }
            }

            possibleDest = new ArrayList<>();
            for(int i = 0; i < t; i++){
                possibleDest.add(Integer.parseInt(br.readLine()));
            }

            dist = new int[n+1];
            Arrays.fill(dist, 100000000);

            dijkstra(s);

            Collections.sort(possibleDest);

            for(int num : possibleDest){
                if(dist[num] % 2 == 1) bw.write(num + " ");
            }
            bw.write("\n");
        }

        br.close();
        bw.close();
    }

    public static void dijkstra(int start){
        boolean[] visited = new boolean[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visited[now.x]) continue;
            visited[now.x] = true;

            for(Node next : graph[now.x]){
                if(!visited[next.x] && dist[next.x] > dist[now.x] + next.weight){
                    dist[next.x] = dist[now.x] + next.weight;
                    pq.offer(new Node(next.x, dist[next.x]));
                }
            }
        }

    }

}
