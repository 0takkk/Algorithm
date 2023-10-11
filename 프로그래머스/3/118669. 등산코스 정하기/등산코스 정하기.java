import java.util.*;

class Solution {
    public static class Node implements Comparable<Node> {
        int state, intensity;

        public Node(int state) {
            this.state = state;
        }

        public Node(int state, int intensity) {
            this.state = state;
            this.intensity = intensity;
        }

        @Override
        public int compareTo(Node o) {
            return this.intensity - o.intensity;
        }
    }

    public static class Edge {
        int x, cost;

        public Edge(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
    }

    public static int N;
    public static ArrayList<Edge>[] graph;
    public static HashSet<Integer> GATES, SUMMITS;
    public static int[] answer;

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        answer = new int[]{0, Integer.MAX_VALUE};

        N = n;
        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int c = path[2];

            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        GATES = new HashSet<>();
        SUMMITS = new HashSet<>();

        for (int gate : gates) {
            GATES.add(gate);
        }

        for (int summit : summits) {
            SUMMITS.add(summit);
        }

        for (int gate : gates) {
            bfs(gate);
        }

        return answer;
    }

    public static void bfs(int startGate) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startGate));
        int[] costs = new int[N+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[startGate] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.state;
            int intensity = node.intensity;

            if(intensity > answer[1]) return;

            if(SUMMITS.contains(now)) {
                if(answer[1] > intensity) {
                    answer[0] = now;
                    answer[1] = intensity;
                }
                else if(answer[1] == intensity) {
                    answer[0] = Math.min(answer[0], now);
                }
                continue;
            }

            for (Edge edge : graph[now]) {
                int next = edge.x;
                int cost = edge.cost;

                if(GATES.contains(next)) continue;

                if(costs[next] > cost) {
                    costs[next] = cost;
                    pq.offer(new Node(next, Math.max(intensity, cost)));
                }
            }
        }
    }
}