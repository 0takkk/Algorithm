import java.io.*;
import java.util.*;

public class Main {

    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        arr = new int[n+1];
        for(int i = 1; i <= n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            if(check(l, r)) sb.append("1\n");
            else sb.append("0\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean check(int l, int r){
        while(l <= r){
            if(arr[l] != arr[r]){
                return false;
            }

            l++;
            r--;
        }

        return true;
    }

}
