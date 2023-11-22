import java.util.*;

class Solution {
    
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

    public static ArrayList<Node>[] graph;
    public static int[] intensities;
    public static boolean[] isGate, isSummit;

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList[n+1];
        intensities = new int[n+1];

        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            intensities[i] = Integer.MAX_VALUE;
        }

        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int cost = path[2];
            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        isGate = new boolean[n+1];
        isSummit = new boolean[n+1];

        for (int gate : gates) {
            isGate[gate] = true;
        }
        for (int summit : summits) {
            isSummit[summit] = true;
        }

        for (int gate : gates) {
            dijkstra(gate);
        }

        int[] answer = new int[] {0, Integer.MAX_VALUE};
        for (int summit : summits) {
            int intensity = intensities[summit];
            if(intensity == answer[1]) {
                answer[0] = Math.min(answer[0], summit);
            }
            else if(intensity < answer[1]) {
                answer[0] = summit;
                answer[1] = intensity;
            }
        }
        
        return answer;
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        intensities[start] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(now.cost > intensities[now.x]) continue;
            if(isSummit[now.x]) continue;

            for (Node next : graph[now.x]) {
                if(intensities[next.x] > Math.max(now.cost, next.cost) && !isGate[next.x]) {
                    intensities[next.x] = Math.max(now.cost, next.cost);
                    pq.offer(new Node(next.x, intensities[next.x]));
                }
            }
        }
    }
    
}