import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int[] count = new int[100001];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }

        int ans = 0;
        int left = 0, right = 0;

        while(right < n){
            while(right < n && count[arr[right]] + 1 <= k){
                count[arr[right]]++;
                right++;
            }

            ans = Math.max(ans, right-left);

            count[arr[left]]--;
            left++;
        }

        System.out.println(ans);
    }

}
