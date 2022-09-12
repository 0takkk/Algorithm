import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] parent, weight;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            weight = new int[n+1];
            parent = new int[n+1];
            for(int i = 1; i <= n; i++) parent[i] = i;

            while(m-->0){
                st = new StringTokenizer(br.readLine());
                char comd = st.nextToken().charAt(0);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());;

                if(comd == '!'){
                    int diff = Integer.parseInt(st.nextToken());

                    union(a, b, diff);
                }else{
                    if(find(a) != find(b)) sb.append("UNKNOWN\n");
                    else sb.append((weight[b] - weight[a])).append("\n");
                }
            }
        }

        System.out.println(sb.toString());
    }

    public static int find(int x){
        if(x == parent[x]) return x;

        int px = find(parent[x]);
        weight[x] += weight[parent[x]];
        return parent[x] = px;
    }

    public static void union(int x, int y, int diff){
         int px = find(x);
         int py = find(y);

         if(px == py) return;

         weight[py] = weight[x] - weight[y] + diff;
         parent[py] = px;
    }
}
