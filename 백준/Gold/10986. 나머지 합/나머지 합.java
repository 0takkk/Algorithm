import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int sum = 0;
        int[] arr = new int[n];

        long cnt = 0;
        int[] mod = new int[m];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            sum = (sum + Integer.parseInt(st.nextToken())) % m;
            arr[i] = sum;

            if(arr[i] == 0) cnt++;

            mod[arr[i]]++;
        }

        for(int i = 0; i < m; i++){
            cnt += (long)mod[i] * (mod[i]-1) / 2;
        }

        System.out.println(cnt);
    }

}