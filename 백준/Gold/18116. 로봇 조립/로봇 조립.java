import java.io.*;
import java.util.*;

public class Main {

    public static final int size = 1000001;
    public static int[] parent, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        parent = new int[size];
        count = new int[size];
        for(int i = 1; i < size; i++){
            parent[i] = i;
            count[i] = 1;
        }

        while(n-->0){
            st = new StringTokenizer(br.readLine());
            char comd = st.nextToken().charAt(0);

            if(comd == 'I'){
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
            else{
                int c = Integer.parseInt(st.nextToken());
                sb.append(count[find(c)]).append("\n");
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

        if(x == y) return;

        if(x > y){
            int tmp = x;
            x = y;
            y = tmp;
        }

        parent[y] = x;
        count[x] += count[y];
    }

}
