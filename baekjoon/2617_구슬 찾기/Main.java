import java.io.*;
import java.util.*;

public class Main {

    public static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] winner = new ArrayList[n+1];
        ArrayList<Integer>[] looser = new ArrayList[n+1];

        for(int i = 1; i <= n; i++){
            winner[i] = new ArrayList<>();
            looser[i] = new ArrayList<>();
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            winner[a].add(b);
            looser[b].add(a);
        }

        int[] win = new int[n+1];
        int[] lose = new int[n+1];

        for(int i = 1; i <= n; i++){
            win[i] = bfs(i, winner);
            lose[i] = bfs(i, looser);
        }

        int ans = 0;
        for(int i = 1; i <= n; i++){
            if(win[i] >= (n/2 + 1)) ans++;
            if(lose[i] >= (n/2 + 1)) ans++;
        }

        System.out.println(ans);
    }

    public static int bfs(int start, ArrayList<Integer>[] graph){
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        int cnt = 0;
        while(!q.isEmpty()){
            int now = q.poll();

            for(int next : graph[now]){
                if(!visited[next]){
                    visited[next] = true;
                    q.offer(next);
                    cnt++;
                }
            }
        }

        return cnt;
    }

}
