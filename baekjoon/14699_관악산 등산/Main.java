import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] height;
    public static HashSet<Integer>[] list;
    public static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        height = new int[n+1];
        list = new HashSet[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            height[i] = Integer.parseInt(st.nextToken());
            list[i] = new HashSet<>();
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(height[a] > height[b]) list[b].add(a);
            else list[a].add(b);
        }

        count = new int[n+1];

        for(int i = 1; i <= n; i++){
            System.out.println(dfs(i));
        }
    }

    public static int dfs(int idx){
        if(count[idx] != 0) return count[idx];

        count[idx] = 1;

        for(int next : list[idx]){
            count[idx] = Math.max(count[idx], dfs(next)+1);
        }
        return count[idx];
    }

}
