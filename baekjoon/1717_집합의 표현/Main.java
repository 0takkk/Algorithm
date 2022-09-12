import java.io.*;
import java.util.*;

public class Main {

    public static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i = 0; i <= n; i++){
            parent[i] = i;
        }

        StringBuilder sb = new StringBuilder();

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int comd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(comd == 0) union(a, b);
            else{
                if(find(a) != find(b)) sb.append("NO\n");
                else sb.append("YES\n");
            }
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
