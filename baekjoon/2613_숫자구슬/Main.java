import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int left = 0, right = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            right += arr[i];
            left = Math.max(left, arr[i]);
        }

        int ans = 987676543;
        int[] ansCount = new int[m];

        while(left <= right){
            int mid = (left + right) / 2;
            int cnt = 0;
            int sum = 0;
            int[] tmp = new int[m];

            for(int i = 0; i < n; i++){
                if(sum + arr[i] <= mid) {
                    sum += arr[i];
                }else{
                    sum = arr[i];
                    cnt++;
                }

                if(cnt >= m) continue;

                tmp[cnt]++;
            }

            if(cnt < m){
                if(ans > mid){
                    ans = mid;
                    ansCount = tmp;
                }
                right = mid-1;
            }
            else{
                left = mid+1;
            }
        }

        int idx = 0;
        for(int i = 0; i < m; i++){
            if(ansCount[i] == 0){
                idx = i-1;
                ansCount[i]++;
                while(true){
                    if(ansCount[idx] != 1){
                        break;
                    }
                    idx--;
                }
                ansCount[idx]--;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans).append("\n");
        for(int i = 0; i < m; i++){
            sb.append(ansCount[i]).append(" ");
        }

        System.out.println(sb.toString());
    }

}
