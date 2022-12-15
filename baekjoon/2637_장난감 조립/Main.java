import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        int num, count;

        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public static int n;
    public static ArrayList<Node>[] list;
    public static int[] counts, degree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        list = new ArrayList[n+1];
        counts = new int[n+1];
        degree = new int[n+1];

        for(int i = 1; i <= n; i++) list[i] = new ArrayList<>();

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
            degree[b]++;
        }

        topology();

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            if(list[i].size() == 0){
                sb.append(i).append(" ").append(counts[i]).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static void topology(){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(n);
        counts[n] = 1;

        while(!q.isEmpty()){
            int now = q.poll();
            int count = counts[now];

            for (Node node : list[now]) {
                int next = node.num;
                int nextCount = node.count;

                counts[next] += (count * nextCount);
                if(--degree[next] == 0) q.offer(next);
            }
        }
    }

}
