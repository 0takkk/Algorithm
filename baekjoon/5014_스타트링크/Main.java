import java.io.*;
import java.util.*;

public class Main {

    public static int f, s, g, u, d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        int ans = bfs();
        System.out.println(ans == -1 ? "use the stairs" : ans);
    }

    private static int bfs() {
        int[] dx = new int[]{u, -d};

        boolean[] visited = new boolean[f+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);
        visited[s] = true;

        int time = 0;
        while(!q.isEmpty()){
            int size = q.size();

            while(size-->0){
                int now = q.poll();

                if(now == g){
                    return time;
                }

                for(int i = 0; i < 2; i++){
                    int next = now + dx[i];

                    if(!isRange(next)) continue;

                    if(!visited[next]){
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }

            time++;
        }

        return -1;
    }

    public static boolean isRange(int x){
        return x >= 1 && x <= f;
     }

}
