import java.io.*;
import java.util.*;

public class Main {

    public static int time = -1;
    public static int[] times, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        times = new int[100001];
        Arrays.fill(times, Integer.MAX_VALUE);
        dp = new int[100001];

        bfs(n, k);

        System.out.println(time);
        System.out.println(dp[k]);
    }

    public static void bfs(int n, int k){
        boolean[] visited = new boolean[100001];
        Queue<Integer> q = new LinkedList<>();
        visited[n] = true;
        dp[n] = 1;
        q.offer(n);

        while(!q.isEmpty()){
            int size = q.size();
            time++;

            for(int i = 0; i < size; i++){
                int now = q.poll();

                if(now == k){
                    return;
                }

                int next;
                for(int j = 0; j < 3; j++){
                    if(j == 0) next = now-1;
                    else if(j == 1) next = now+1;
                    else next = now*2;

                    if(next < 0 || next > 100000) continue;
                    if(time > times[next]) continue;
                    dp[next] += dp[now];
                    times[next] = time;

                    if(!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
        }
    }

}
