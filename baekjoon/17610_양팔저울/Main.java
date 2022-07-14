import java.io.*;
import java.util.*;

public class Main {

    public static int n, size;
    public static int[] arr;
    public static boolean[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        arr = new int[n];
        size = 0;

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            size += arr[i];
        }

        num = new boolean[size+1];

        dfs(0, 0);

        int count = 0;
        for(int i = 1; i <= size; i++){
            if(!num[i]) count++;
        }

        System.out.println(count);
    }

    public static void dfs(int idx, int sum){
        if(idx == n){
            if(sum > 0) num[sum] = true;
            return;
        }

        dfs(idx+1, sum);
        dfs(idx+1, sum - arr[idx]);
        dfs(idx+1, sum + arr[idx]);
    }

}
