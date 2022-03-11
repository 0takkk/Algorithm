package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(6, 4, 6, 2, new int[][] {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41},
                {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}));
    }

    public static ArrayList<Node>[] list;

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

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < fares.length; i++){
            int c = fares[i][0];
            int d = fares[i][1];
            int f = fares[i][2];

            list[c].add(new Node(d, f));
            list[d].add(new Node(c, f));
        }

        int[] startA = new int[n+1];
        int[] startB = new int[n+1];
        int[] start = new int[n+1];

        Arrays.fill(startA, Integer.MAX_VALUE);
        Arrays.fill(startB, Integer.MAX_VALUE);
        Arrays.fill(start, Integer.MAX_VALUE);

        startA = dijkstra(a, startA);
        startB = dijkstra(b, startB);
        start = dijkstra(s, start);

        for(int i = 1; i <= n; i++){
            answer = Math.min(answer, start[i] + startA[i] + startB[i]);
        }

        return answer;
    }

    public static int[] dijkstra(int start, int[] dist){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.weight > dist[now.x]) continue;

            for(Node next : list[now.x]){
                int weight = dist[now.x] + next.weight;

                if(dist[next.x] > weight){
                    dist[next.x] = weight;
                    pq.offer(new Node(next.x, weight));
                }
            }
        }

        return dist;
    }

}
