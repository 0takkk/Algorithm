import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] degree = new int[n+1];
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        while(m-->0) {
            st = new StringTokenizer(br.readLine());
            int big = Integer.parseInt(st.nextToken());
            int small = Integer.parseInt(st.nextToken());

            degree[small]++;
            graph[big].add(small);
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            if(degree[i] == 0) q.offer(i);
        }

        StringBuilder sb = new StringBuilder();

        while(!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");

            for (int next : graph[now]) {
                if(--degree[next] == 0) q.offer(next);
            }
        }

        System.out.println(sb.toString());
    }

}