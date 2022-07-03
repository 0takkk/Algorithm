import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] arr, parent, depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        char[] tree = br.readLine().toCharArray();

        arr = new int[2*n+1];
        parent = new int[n+1];
        depth = new int[n+1];

        Stack<Integer> parentStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();
        parentStack.push(0);
        depthStack.push(0);

        int idx = 1;
        for(int i = 0; i < tree.length; i++){
            if(tree[i] == '0'){
                arr[i+1] = idx;
                parent[idx] = parentStack.peek();
                depth[idx] = depthStack.peek()+1;

                parentStack.push(idx);
                depthStack.push(depth[idx]);
                idx++;
            }
            else{
                arr[i+1] = parentStack.pop();
                depthStack.pop();
            }
        }

        st = new StringTokenizer(br.readLine());
        int x = arr[Integer.parseInt(st.nextToken())];
        int y = arr[Integer.parseInt(st.nextToken())];

        int target = lca(x, y);

        int in = 0, out = 0;
        for(int i = 1; i <= 2*n; i++){
            if(arr[i] == target && tree[i-1] == '0'){
                in = i;
            }
            else if(arr[i] == target && tree[i-1] == '1'){
                out = i;
                break;
            }
        }

        System.out.println(in + " " + out);
    }

    public static int lca(int x, int y){
        while(true){
            if(x == y) return x;

            if(depth[x] == depth[y]){
                x = parent[x];
                y = parent[y];
            }
            else if(depth[x] > depth[y]){
                x = parent[x];
            }
            else{
                y = parent[y];
            }
        }
    }

}
