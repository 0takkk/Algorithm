import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Integer>[] graph;
    public static int[] degree;
    public static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        degree = new int[n+1];

        for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int pre = Integer.parseInt(st.nextToken());
            while(size-->1){
                int next = Integer.parseInt(st.nextToken());
                graph[pre].add(next);
                pre = next;
                degree[next]++;
            }
        }

        list = new ArrayList<>();
        boolean flag = topology();

        if(flag){
            StringBuilder sb = new StringBuilder();
            for (int num : list) sb.append(num).append("\n");

            System.out.println(sb.toString());
        } else{
            System.out.println(0);
        }

    }

    public static boolean topology(){
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++){
            if(degree[i] == 0) q.offer(i);
        }

        while(!q.isEmpty()){
            int now = q.poll();
            list.add(now);

            for (int next : graph[now]) {
                if(--degree[next] == 0) q.offer(next);
            }
        }

        return list.size() == n;
    }

}
