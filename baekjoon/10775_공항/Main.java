import java.io.*;
import java.util.*;

public class Main {

    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        parent = new int[g+1];
        for(int i = 1; i <= g; i++)
            parent[i] = i;

        int count = 0;
        for(int i = 0; i < p; i++){
            int gate = Integer.parseInt(br.readLine());

            int emptyGate = find(gate);

            if(emptyGate == 0) break;

            count++;
            union(emptyGate-1, emptyGate);
        }

        System.out.println(count);
    }

    public static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y)
            parent[y] = x;
    }

}
