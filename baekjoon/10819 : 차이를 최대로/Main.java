import java.io.*;
import java.util.*;

public class Main {

    public static int n, result;
    public static int[] arr;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            visited[i] = true;
            backTracking(i, 0, 0);
            visited[i] = false;
        }
        System.out.println(result);
    }

    public static void backTracking(int preIdx, int sum, int cnt){
        if(cnt == n-1){
            result = Math.max(result, sum);
            return;
        }

        for(int i = 0; i < n; i++){
            if(!visited[i] && preIdx != i){
                visited[i] = true;
                backTracking(i, sum + Math.abs(arr[preIdx] - arr[i]), cnt+1);
                visited[i] = false;
            }
        }
    }

}
