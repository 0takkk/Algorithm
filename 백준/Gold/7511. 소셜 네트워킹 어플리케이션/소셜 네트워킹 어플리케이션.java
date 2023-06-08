import java.io.*;
import java.util.*;

public class Main {

    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int idx = 1;
        while(t-->0){
            sb.append("Scenario ").append(idx++).append(":\n");
            int n = Integer.parseInt(br.readLine());
            int k = Integer.parseInt(br.readLine());

            parent = new int[n];
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }

            while(k-->0){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            int m = Integer.parseInt(br.readLine());
            while(m-->0){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                int ans = find(u) == find(v) ? 1 : 0;
                sb.append(ans).append("\n");
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y) parent[y] = x;
    }

}
