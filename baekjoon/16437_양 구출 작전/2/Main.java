import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static ArrayList<Integer>[] graph;
    public static long[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        count = new long[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 2; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            char ani = st.nextToken().charAt(0);
            long cnt = Long.parseLong(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if(ani == 'W') cnt *= -1;

            count[i] = cnt;
            graph[num].add(i);
        }

        dfs(1, 0);
        System.out.println(count[1]);
    }

    public static void dfs(int now, int pre){
        for (int next : graph[now]) {
            dfs(next, now);
        }

        if(count[now] > 0){
            count[pre] += count[now];
        }
    }

}
