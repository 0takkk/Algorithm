package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(5, new int[][] {{1,2,1},{2,3,3},{5,2,2},{1,4,2}, {5,3,1}, {5,4,2}},3));
        System.out.println(solution(6, new int[][] {{1,2,1},{1,3,2},{2,3,2},{3,4,3}, {3,5,2}, {3,5,3}, {5,6,1}}, 4));
    }

    public static class Node implements Comparable<Node>{
        int x, weight;

        public Node(int x, int weight) {
            this.x = x;
            this.weight = weight;
        }

        public int compareTo(Node n){
            return weight - n.weight;
        }
    }

    public static ArrayList<Node>[] graph;
    public static int[] dist;

    public static int solution(int N, int[][] road, int K) {
        int answer = 0;

        graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for(int i = 0; i < road.length; i++){
            int a = road[i][0];
            int b = road[i][1];
            int weight = road[i][2];

            graph[a].add(new Node(b, weight));
            graph[b].add(new Node(a, weight));
        }

        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        dijkstra();

        for (int d : dist) {
            if(d <= K){
                answer++;
            }
        }

        return answer;
    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(dist[now.x] < now.weight) continue;

            for(Node next : graph[now.x]){
                if(dist[next.x] > dist[now.x] + next.weight){
                    dist[next.x] = dist[now.x] + next.weight;
                    pq.offer(new Node(next.x, dist[next.x]));
                }
            }
        }
    }

}
