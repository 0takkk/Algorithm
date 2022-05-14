import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node>{
        int x, cost, run;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }

        public Node(int x, int cost, int run) {
            this.x = x;
            this.cost = cost;
            this.run = run;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static int n;
    public static ArrayList<Node>[] list;
    public static int[] fox;
    public static int[][] wolf;
    public static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        fox = new int[n+1];
        wolf = new int[n+1][2];

        Arrays.fill(fox, MAX);
        for(int i = 0; i <= n; i++){
            Arrays.fill(wolf[i], MAX);
        }
        fox[1] = 0;
        wolf[1][0] = 0;

        foxTime();
        wolfTime();

        int count = 0;
        for(int i = 2; i <= n; i++){
            if(fox[i] < Math.min(wolf[i][0], wolf[i][1])){
                count++;
            }
        }

        System.out.println(count);
    }

    public static void foxTime(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(fox[now.x] < now.cost) continue;

            for(Node next : list[now.x]){
                if(fox[next.x] > fox[now.x] + next.cost * 2){
                    fox[next.x] = fox[now.x] + next.cost * 2;
                    pq.offer(new Node(next.x, fox[next.x]));
                }
            }
        }
    }

    public static void wolfTime(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int run = 1 - now.run;
            int mul = run == 1 ? 1 : 4;

            if(wolf[now.x][now.run] < now.cost) continue;

            for(Node next : list[now.x]){
                if(wolf[next.x][run] > wolf[now.x][now.run] + next.cost * mul){
                    wolf[next.x][run] = wolf[now.x][now.run] + next.cost * mul;
                    pq.offer(new Node(next.x, wolf[next.x][run], run));
                }
            }
        }
    }

}
