import java.io.*;
import java.util.*;

public class Main {

    public static int[] parent, enemy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        enemy = new int[n+1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int ans = 1;
        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(find(a) == find(b)) {
                ans = 0;
                break;
            }

            union(enemy[a], b);
            union(enemy[b], a);
            enemy[a] = b;
            enemy[b] = a;
        }

        System.out.println(ans);
    }

    public static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        if(x == 0) return;

        x = find(x);
        y = find(y);

        if(x != y){
            parent[y] = x;
        }
    }

}
