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

    public static int len, answer[];
    public static ArrayList<Node>[] graph;
    public static HashSet<Integer> gateSet, summitSet;

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        len = n;
        graph = new ArrayList[len+1];
        gateSet = new HashSet<>();
        summitSet = new HashSet<>();

        for(int i = 1; i <= len; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int c = path[2];

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        for (int gate : gates) {
            gateSet.add(gate);
        }

        for (int summit : summits) {
            summitSet.add(summit);
        }

        answer = new int[] {0, Integer.MAX_VALUE};
        for (int gate : gates) {
            bfs(gate);
        }

        return answer;
    }

    public static void bfs(int start) {
        int[] visited = new int[len+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[start] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(now.cost > answer[1]) return;

            if(summitSet.contains(now.x)) {
                if(now.cost == answer[1]) {
                    answer[0] = Math.min(answer[0], now.x);
                }
                else if(now.cost < answer[1]) {
                    answer[0] = now.x;
                    answer[1] = now.cost;
                }
                continue;
            }

            for (Node next : graph[now.x]) {
                if(visited[next.x] > next.cost && !gateSet.contains(next.x)) {
                    visited[next.x] = next.cost;
                    pq.offer(new Node(next.x, Math.max(now.cost, next.cost)));
                }
            }
        }
    }

    
}