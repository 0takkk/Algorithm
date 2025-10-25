import java.util.*;

class Solution {
    
    public class Edge implements Comparable<Edge> {
        int to, cost;
        
        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }
    
    public List<Edge>[] graph;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        
        for(int[] fare : fares) {
            int to = fare[0];
            int from = fare[1];
            int cost = fare[2];
            
            graph[to].add(new Edge(from, cost));
            graph[from].add(new Edge(to, cost));
        }
        
        int[][] dist = new int[n+1][n+1];
        
        for(int i = 1; i <= n; i++) {
            dist[i] = dijkstra(i, n);
        }
        
        answer = dist[s][a] + dist[s][b];
        for(int i = 1; i <= n; i++) {
            if(s != i) {
                answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);            
            }
        }
        
        return answer;
    }
    
    public int[] dijkstra(int to, int n) {
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(to, 0));
        dist[to] = 0;
        
        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            
            if(dist[now.to] < now.cost) {
                continue;
            }
            
            for(Edge next : graph[now.to]) {
                if(next.to != to) {
                    if(dist[next.to] > dist[now.to] + next.cost) {
                        dist[next.to] = dist[now.to] + next.cost;
                        pq.offer(new Edge(next.to, dist[next.to]));
                    }
                }
            }
        }
        
        return dist;
    }
}