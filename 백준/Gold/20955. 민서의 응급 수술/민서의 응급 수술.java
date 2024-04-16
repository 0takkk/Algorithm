import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }

        int ans = 0;

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(find(a) != find(b)){
                union(a, b);
            } else{
                ans++;
            }
        }

        int root = find(1);
        for(int i = 1; i <= n; i++){
            if(find(i) != root){
                ans++;
                union(root, i);
            }
        }

        System.out.println(ans);
    }

    public static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        parent[y] = x;
    }

}