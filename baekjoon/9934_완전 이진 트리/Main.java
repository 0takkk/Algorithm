import java.io.*;
import java.util.*;

public class Main {

    public static int k;
    public static int[] arr;
    public static StringBuilder[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());
        int n = (int)Math.pow(2, k) - 1;

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ans = new StringBuilder[k];
        for(int i = 0; i < k; i++){
            ans[i] = new StringBuilder();
        }

        rec(0, n, 0);

        for(int i = 0; i < k; i++){
            System.out.println(ans[i].toString());
        }
    }

    public static void rec(int left, int right, int idx){
        if(idx == k) return;

        int mid = (left + right) / 2;
        ans[idx].append(arr[mid]+ " ");

        rec(left, mid-1, idx+1);
        rec(mid+1, right, idx+1);
    }

}
