import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Integer>[] graph;
    public static int[] degree, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        degree = new int[n+1];
        ans = new int[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            degree[b]++;
        }

        topology();

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            sb.append(ans[i]).append(" ");
        }

        System.out.println(sb.toString());
    }

    public static void topology(){
        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 1; i <= n; i++){
            if(degree[i] == 0) q.offer(i);
        }

        int time = 1;

        while(!q.isEmpty()){
            int size = q.size();
            while(size-->0) {
                int now = q.poll();

                ans[now] = time;

                for (int next : graph[now]) {
                    if (--degree[next] == 0) {
                        q.offer(next);
                    }
                }
            }
            time++;
        }
    }

}
