import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static boolean[][] edge;
    public static int[] degree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while(t-->0){
            n = Integer.parseInt(br.readLine());

            edge = new boolean[n+1][n+1];
            degree = new int[n+1];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                int num = Integer.parseInt(st.nextToken());
                degree[num] = i;

                for(int j = 1; j <= n; j++){
                    if(num != j && !edge[j][num]){
                        edge[num][j] = true;
                    }
                }
            }

            int m = Integer.parseInt(br.readLine());
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                swap(a, b);
            }
            System.out.println(topologicalSort());
        }
    }

    public static String topologicalSort(){
        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++){
            if(degree[i] == 0){
                q.offer(i);
            }
        }

        for(int i = 1; i<= n; i++){
            if(q.size() == 0) return "IMPOSSIBLE";
            if(q.size() > 1) return "?";

            int now = q.poll();
            sb.append(now + " ");

            for(int j = 1; j <= n; j++){
                if(edge[now][j]){
                    if(--degree[j] == 0){
                        q.offer(j);
                    }
                }
            }
        }

        return sb.toString();
    }

    public static void swap(int a, int b){
        if(!edge[a][b]){
            edge[a][b] = true;
            edge[b][a] = false;
            degree[a]--;
            degree[b]++;
        }
        else{
            edge[a][b] = false;
            edge[b][a] = true;
            degree[a]++;
            degree[b]--;
        }
    }

}
