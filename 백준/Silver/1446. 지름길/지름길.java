import java.io.*;
import java.util.*;

public class Main {

    public static int d, ans;
    public static int[][] shortcut;
    public static boolean[] hasShortcut;
    public static HashSet<Integer>[] endPoint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        shortcut = new int[d+1][d+1];
        ans = Integer.MAX_VALUE;
        hasShortcut = new boolean[d+1];
        endPoint = new HashSet[d+1];

        while(n-->0){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            if(start > d || end > d || end - start + 1 <= time) continue;

            shortcut[start][end] = shortcut[start][end] == 0 ? time : Math.min(shortcut[start][end], time);
            hasShortcut[start] = true;

            if(endPoint[start] == null) endPoint[start] = new HashSet<>();
            endPoint[start].add(end);
        }

        dfs(0, 0);
        System.out.println(ans);
    }

    public static void dfs(int now, int time){
        if(time >= ans) return;

        if(now == d){
            ans = time;
            return;
        }

        dfs(now+1, time+1);
        if(hasShortcut[now]){
            for (int next : endPoint[now]) {
                dfs(next, time+shortcut[now][next]);
            }
        }
    }

}
