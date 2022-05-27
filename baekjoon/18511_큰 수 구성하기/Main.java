import java.io.*;
import java.util.*;

public class Main {

    public static int n, k, result;
    public static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        num = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);

        dfs(0);
        System.out.println(result);
    }

    public static void dfs(int now){
        if(now > n) return;

        if(result < now) result = now;

        for(int i = k-1; i >= 0; i--){
            dfs(now*10 + num[i]);
        }
    }

}
