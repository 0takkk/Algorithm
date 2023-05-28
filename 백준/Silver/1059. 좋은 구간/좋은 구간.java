import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int l = Integer.parseInt(br.readLine());
        int[] arr = new int[l];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < l; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int n = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int left = 0;
        int right = l-1;

        while(left < right){
            int mid = (left + right) / 2;

            if(arr[mid] >= n){
                right = mid;
            }else{
                left = mid+1;
            }
        }

        if(arr[left] == n){
            System.out.println(0);
            return;
        }

        int smallCnt = left == 0 ? n : n - arr[left-1];
        int bigCnt = arr[left] - n;

        int ans = (smallCnt-1) * bigCnt + bigCnt-1;

        System.out.println(ans);
    }
}
