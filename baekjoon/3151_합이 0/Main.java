import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long ans = 0;

        for(int i = 0; i < n; i++){
            if(arr[i] > 0) break;

            int left = i+1;
            int right = n-1;

            while(left < right){
                int sum = arr[i] + arr[left] + arr[right];

                if(sum == 0){
                    if(arr[left] == arr[right]){
                        ans += ((long) (right - left + 1) * (right - left) / 2);
                        break;
                    }

                    int leftCnt = 1;
                    int rightCnt = 1;

                    while(left + 1 < right && arr[left] == arr[left+1]){
                        leftCnt++;
                        left++;
                    }

                    while(left < right-1 && arr[right] == arr[right-1]){
                        rightCnt++;
                        right--;
                    }

                    ans += ((long) leftCnt * rightCnt);
                }

                if(sum > 0) right--;
                else left++;
            }
        }

        System.out.println(ans);
    }

}
