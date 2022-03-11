package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        System.out.println(solution(4, new int[][] {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}}));
        System.out.println(solution(5, new int[][] { {0, 1, 5}, {1, 2, 3}, {2, 3, 3}, {3, 1, 2}, {3, 0, 4}, {2, 4, 6}, {4, 0, 7}}));
    }

    public static class Node implements Comparable<Node>{
        int x, y, weight;

        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static int[] parent;

    public static int solution(int n, int[][] costs) {
        int answer = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        parent = new int[n];

        for(int i = 0; i < n; i++)
            parent[i] = i;

        for (int[] cost : costs) {
            pq.offer(new Node(cost[0], cost[1], cost[2]));
        }

        while(!pq.isEmpty()){
            Node node = pq.poll();

            if(find(node.x) == find(node.y)) continue;

            union(node.x, node.y);
            answer += node.weight;
        }

        return answer;
    }

    public static int find(int idx){
        if(parent[idx] == idx) return idx;
        return parent[idx] = find(parent[idx]);
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a != b) parent[b] = a;
    }
}
