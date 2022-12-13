import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        HashMap<Integer, Long> map = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            int num = Integer.parseInt(st.nextToken());
            map.put(num, map.getOrDefault(num, 0L) + 1);
        }

        long[] arr = new long[n+1];
        for (int key : map.keySet()) {
            long val = map.get(key);
            for(int i = 1; i <= n; i+=key){
                arr[i] += val;
            }
        }

        for(int i = 2; i <= n; i++){
            arr[i] += arr[i-1];
        }

        StringBuilder sb = new StringBuilder();
        int q = Integer.parseInt(br.readLine());
        while(q-->0){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            sb.append(arr[r+1] - arr[l]).append("\n");
        }

        System.out.println(sb.toString());
    }

}
