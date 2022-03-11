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
        int answer = 0;

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

        int minA = dijkstra(s, a);
        int minB = dijkstra(s, b);

        int total = minA + minB;

        for(int i = 1; i <= n; i++){
            if(i == s) continue;

            int costSum = dijkstra(s, i);

            int costA = dijkstra(i, a);
            int costB = dijkstra(i, b);

            if(total > costSum + costA + costB){
                total = costSum + costA + costB;
            }
        }

        answer = total;

        return answer;
    }

    public static int dijkstra(int start, int destination){
        boolean[] visited = new boolean[list.length];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        int[] dist = new int[list.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.x == destination) break;
            
            if(visited[now.x]) continue;
            visited[now.x] = true;

            for(Node next : list[now.x]){
                if(!visited[next.x] && dist[next.x] > dist[now.x] + next.weight){
                    dist[next.x] = dist[now.x] + next.weight;
                    pq.offer(new Node(next.x, dist[next.x]));
                }
            }
        }

        return dist[destination];
    }

}
