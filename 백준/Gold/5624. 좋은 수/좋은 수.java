import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        boolean[] visited = new boolean[400002];
        int ans = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());

            for(int j = 0; j < i; j++){
                if(visited[arr[i] - arr[j] + 200000]){
                    ans++;
                    break;
                }
            }

            for(int j = 0; j <= i; j++){
                visited[arr[i] + arr[j] + 200000] = true;
            }
        }

        System.out.println(ans);
    }

}
