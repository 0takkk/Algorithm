import java.io.*;
import java.util.*;

public class Main {

    public static int n, arr[];
    public static long[] count = new long[21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        count[arr[0]] = 1;
        cal(0);

        System.out.println(count[arr[n-1]]);
    }

    public static void cal(int idx){
        if(idx == n-2) return;

        long[] tmp = new long[21];
        for(int i = 0; i <= 20; i++){
            if(count[i] != 0){
                if(i - arr[idx+1] >= 0){
                    tmp[i - arr[idx+1]] += count[i];
                }
                if(i + arr[idx+1] <= 20){
                    tmp[i + arr[idx+1]] += count[i];
                }
            }
        }

        count = tmp.clone();
        cal(idx+1);
    }
    
}
