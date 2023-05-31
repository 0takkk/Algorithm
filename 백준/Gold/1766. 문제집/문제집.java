import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        int[] degree = new int[n+1];

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            degree[to]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i <= n; i++){
            if(degree[i] == 0) pq.offer(i);
        }

        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()){
            int now = pq.poll();
            sb.append(now).append(" ");

            for (int next : graph[now]) {
                if(--degree[next] == 0) pq.offer(next);
            }
        }

        System.out.println(sb.toString());
    }
}
