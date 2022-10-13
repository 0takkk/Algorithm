import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= t; tc++) {
            int ans = -1;
            int n = Integer.parseInt(br.readLine());

            int max = 0;
            int[] arr = new int[n];
            boolean flag = true;

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                arr[i] = Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
                max = Math.max(max, arr[i]);

                if(i != 0 && arr[i] % 2 != arr[i-1] % 2) flag = false;
            }

            if(flag){
                ans = 0;
                int sum = 0;
                while(true){
                    // 가장 큰 수보다 크면서 누적값과 차이가 짝수여야 원점 가능
                    if(sum >= max && (sum - max) % 2 == 0) break;
                    sum += ++ans;
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

}
