import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        int cnt = bfs(1);
        System.out.println(cnt-1);
    }

    public static int bfs(int start){
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];

        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()){
            int now = q.poll();

            cnt++;

            for(int next : list[now]){
                if(!visited[next]){
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }

        return cnt;
    }

}
