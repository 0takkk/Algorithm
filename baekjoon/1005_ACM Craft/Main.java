import java.io.*;
import java.util.*;

public class Main {

    public static int n, w;
    public static int[] building, degree, time;
    public static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            building = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                building[i] = Integer.parseInt(st.nextToken());
            }

            degree = new int[n+1];
            graph = new ArrayList[n+1];

            for(int i = 1; i <= n; i++){
                graph[i] = new ArrayList<>();
            }

            while(k-->0){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a].add(b);
                degree[b]++;
            }

            w = Integer.parseInt(br.readLine());
            time = new int[n+1];

            topology();

            sb.append(time[w]).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void topology(){
        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 1; i <= n; i++){
            if(degree[i] == 0){
                q.offer(i);
                time[i] = building[i];
                if(i == w) return;
            }
        }

        while(!q.isEmpty()){
            int now = q.poll();

            if(now == w) return;

            for(int next : graph[now]){
                time[next] = Math.max(time[next], time[now]);
                if(--degree[next] == 0){
                    q.offer(next);
                    time[next] += building[next];
                }
            }
        }
    }

}
