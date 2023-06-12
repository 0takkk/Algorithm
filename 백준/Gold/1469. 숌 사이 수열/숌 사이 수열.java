import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] arr, result;
    public static boolean[] visited;
    public static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        result = new int[2*n];
        Arrays.fill(result, -1);
        visited = new boolean[n];

        dfs(0);

        if(!flag) System.out.println(-1);
    }

    public static void dfs(int idx){
        if(idx == 2*n){
            if(!flag) {
                flag = true;
                StringBuilder sb = new StringBuilder();
                for (int i : result) {
                    sb.append(i).append(" ");
                }

                System.out.println(sb.toString());
            }
            return;
        }

        if(result[idx] == -1) {
            for (int i = 0; i < n; i++) {
                if (visited[i]) continue;

                if (check(idx, arr[i])) {
                    visited[i] = true;
                    result[idx] = result[idx + arr[i] + 1] = arr[i];

                    dfs(idx + 1);

                    visited[i] = false;
                    result[idx] = result[idx + arr[i] + 1] = -1;
                }
            }
        }
        else{
            dfs(idx+1);
        }
    }

    public static boolean check(int idx, int num){
        if(idx + num + 1 >= 2*n || result[idx + num + 1] != -1) return false;
        return true;
    }

}
