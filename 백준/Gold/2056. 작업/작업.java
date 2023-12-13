import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] times = new int[n+1];
        int[] workTimes = new int[n+1];
        int[] degree = new int[n+1];
        ArrayList<Integer>[] graph = new ArrayList[n+1];

        Queue<Integer> q = new ArrayDeque<>();

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());

            graph[i] = new ArrayList<>();
            int k = Integer.parseInt(st.nextToken());
            if(k == 0) {
                workTimes[i] = times[i];
                q.offer(i);
            }
            while(k-->0) {
                int work = Integer.parseInt(st.nextToken());
                degree[i]++;
                graph[work].add(i);
            }
        }

        while(!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph[now]) {
                degree[next]--;
                workTimes[next] = Math.max(workTimes[next], workTimes[now]);

                if(degree[next] == 0) {
                    q.offer(next);
                    workTimes[next] += times[next];
                }
            }
        }

        int ans = 0;
        for (int workTime : workTimes) {
            ans = Math.max(ans, workTime);
        }

        System.out.println(ans);
    }

}