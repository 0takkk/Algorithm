import java.io.*;
import java.util.*;

public class Main {

    public static int n, x, y, result;
    public static int[] arr;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        arr = new int[2*n + 1];
        visited = new boolean[2*n+1];
        int target = y-x-1;

        arr[x] = arr[y] = target;
        visited[target] = true;

        backTracking(1);

        System.out.println(result);
    }

    public static void backTracking(int idx) {
        if (idx == 2*n) {
            result++;
            return;
        }

        if(arr[idx] == 0){
            for(int i = 1; i <= n; i++){
                if(visited[i]) continue;

                if(idx+i+1 <= 2*n && arr[idx+i+1] == 0){
                    arr[idx] = arr[idx+i+1] = i;
                    visited[i] = true;

                    backTracking(idx+1);

                    arr[idx] = arr[idx+i+1] = 0;
                    visited[i] = false;
                }
            }
        }
        else backTracking(idx+1);
    }

}
