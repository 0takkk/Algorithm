import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static int[] arr, selected;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        selected = new int[m];

        dfs(0);
        System.out.println(sb.toString());
    }

    public static void dfs(int idx){
        if(idx == m){
            for(int i = 0; i < m; i++){
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < n; i++){
            selected[idx] = arr[i];
            dfs(idx+1);
        }
    }

}
