import java.io.*;
import java.util.*;

public class Main {

    public static int n, num[];
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        num = new int[n+1];
        for(int i = 1; i <= n; i++){
            num[i] = 1;
        }

        dfs(0, new int[n]);
        System.out.println(sb.toString());
    }

    public static void dfs(int idx, int[] arr){
        if(idx == n){
            StringBuilder tmp = new StringBuilder();
            for(int i = 0; i < n; i++){
                tmp.append(arr[i] + " ");
            }

            sb.append(tmp.toString().substring(0, tmp.length()-1) + "\n");
            return;
        }

        for(int i = 1; i <= n; i++){
            if(num[i] > 0){
                num[i]--;
                arr[idx] = i;
                dfs(idx+1, arr);
                num[i]++;
            }
        }

    }

}
