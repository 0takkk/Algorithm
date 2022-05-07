import java.util.*;


public class Main {
    public static void main(String[] args) {
        System.out.println(solution(3, 1, 3, new int[][] {{1,2,2}, {3,2,3}}, new int[] {2}));
        System.out.println(solution(4, 1, 4, new int[][] {{1, 2, 1}, {3, 2, 1}, {2, 4, 1}}, new int[] {2, 3}));
        System.out.println(solution(5, 1, 5, new int[][] {{1, 2, 1}, {2, 3, 1}, {3, 2, 1}, {3, 5, 1},{1, 5, 10}}, new int[] {3}));
    }

    public static int result;
    public static ArrayList<Node>[] graph;
    public static HashSet<Integer> trapSet;

    public static class Node implements Comparable<Node>{
        int idx;  // 현재 위치
        int cost;  // 비용
        boolean state;  // 방향, 트랩
        HashMap<Integer, Integer> trap;  // trap 번호, 수

        public Node(int idx, int cost, boolean state) {
            this.idx = idx;
            this.cost = cost;
            this.state = state;
        }

        public Node(int idx, int cost, boolean state, HashMap<Integer, Integer> trap) {
            this.idx = idx;
            this.cost = cost;
            this.state = state;
            this.trap = trap;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static int solution(int n, int start, int end, int[][] roads, int[] traps) {
        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        int[][] map = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            Arrays.fill(map[i], 5000);
        }

        for (int[] road : roads) {
            int p = road[0];
            int q = road[1];
            int s = road[2];

            map[p][q] = Math.min(map[p][q], s);
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(map[i][j] != 5000){
                    graph[i].add(new Node(j, map[i][j], true));
                    graph[j].add(new Node(i, map[i][j], false));
                }
            }
        }

        trapSet = new HashSet<>();
        for (int trap : traps) {
            trapSet.add(trap);
        }

        game(start, end, n);
        return result;
    }

    public static void game(int start, int end, int n){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, true, new HashMap<>()));

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int idx = now.idx;
            int cost = now.cost;
            boolean state;
            HashMap<Integer, Integer> trap = now.trap;

            HashMap<Integer, Integer> tmp = new HashMap<>();
            for (int key : trap.keySet()) {
                tmp.put(key, trap.get(key));
            }

            if(trapSet.contains(idx)){
                tmp.put(idx, tmp.getOrDefault(idx, 0) + 1);
            }

            if(idx == end){
                result = cost;
                return;
            }

            if(tmp.get(idx) != null && tmp.get(idx) % 2 != 0){
                state = false;
            } else state = true;

            if(tmp.get(idx) != null && tmp.get(idx) > 4) continue;

            for(Node next : graph[idx]){
                boolean nextState = tmp.getOrDefault(next.idx, 0) % 2 == 0;

                // 비활 && 비활
                if(state && nextState){
                    if(!next.state) continue;

                    pq.offer(new Node(next.idx, cost + next.cost, state, tmp));
                }
                // 비할 && 활
                else if((state && !nextState) || (!state && nextState)){
                    if(next.state) continue;

                    pq.offer(new Node(next.idx, cost+next.cost, state, tmp));
                }
                // 활 && 활
                else{
                    if(!next.state) continue;

                    pq.offer(new Node(next.idx, cost+next.cost, state, tmp));
                }
            }

        }

    }

}
