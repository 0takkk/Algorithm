import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        int[] solution = solution(5, new int[][]{{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}}, new int[]{1, 3, 5}, 5);
        for (int i : solution) {
            System.out.print(i + " ");
        }
    }

    public static class Node{
        int x, dist;

        public Node(int x, int dist) {
            this.x = x;
            this.dist = dist;
        }
    }

    public static ArrayList<Integer>[] graph;
    public static int[] dists;
    public static HashSet<Integer> set;

    public static int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = {};

        graph = new ArrayList[n+1];
        dists = new int[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
            dists[i] = -1;
        }

        for (int[] road : roads) {
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }

        set = new HashSet<>();
        for (int source : sources) {
            set.add(source);
        }

        bfs(n, destination);

        answer = new int[sources.length];
        for(int i = 0; i < answer.length; i++){
            answer[i] = dists[sources[i]];
        }

        return answer;
    }

    public static void bfs(int n, int destination){
        boolean[] visited = new boolean[n+1];
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(destination, 0));
        visited[destination] = true;

        int cnt = 0;

        while(!q.isEmpty()){
            Node now = q.poll();
            int x = now.x;
            int dist = now.dist;

            if(set.contains(x)){
                dists[x] = dist;
                cnt++;

                if(cnt == set.size()) return;
            }

            for (int next : graph[x]) {
                if(!visited[next]){
                    visited[next] = true;
                    q.offer(new Node(next, dist+1));
                }
            }
        }
    }
}
