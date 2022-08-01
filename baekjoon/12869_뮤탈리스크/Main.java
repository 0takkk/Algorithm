import java.io.*;
import java.util.*;

public class Main {

    public static int ans;
    public static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] scv = new int[3];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            scv[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[61][61][61];
        ans = Integer.MAX_VALUE;

        dfs(scv[0], scv[1], scv[2], 0);
        System.out.println(ans);
    }

    public static void dfs(int h1, int h2, int h3, int cnt){
        h1 = Math.max(h1, 0);
        h2 = Math.max(h2, 0);
        h3 = Math.max(h3, 0);
        int max = Math.max(Math.max(h1, h2), h3);
        int min = Math.min(Math.min(h1, h2), h3);
        int mid = h1 + h2 + h3 - max - min;

        h1 = max;
        h2 = mid;
        h3 = min;

        if(h1 == 0){
            ans = Math.min(ans, cnt);
        }

        if(visited[h1][h2][h3]) return;
        visited[h1][h2][h3] = true;

        if(ans < cnt) return;

        dfs(h1-9, h2-3, h3-1, cnt+1);
        dfs(h1-9, h2-1, h3-3, cnt+1);
        dfs(h1-3, h2-9, h3-1, cnt+1);
        dfs(h1-3, h2-1, h3-9, cnt+1);
        dfs(h1-1, h2-9, h3-3, cnt+1);
        dfs(h1-1, h2-3, h3-9, cnt+1);
    }

}
