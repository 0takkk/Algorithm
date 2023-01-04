import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while(t-->0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] arr1 = new int[n];
            int[] arr2 = new int[m];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                arr1[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < m; i++){
                arr2[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr1);
            Arrays.sort(arr2);

            int ans = 0;

            for(int i = 0; i < n; i++){
                int a = arr1[i];
                int cnt = 0;

                int left = 0;
                int right = m-1;

                while(left <= right){
                    int mid = (left + right) / 2;

                    if(arr2[mid] < a) {
                        left = mid+1;
                        cnt = Math.max(cnt, mid+1);
                    }
                    else right = mid-1;
                }

                ans += cnt;
            }
            
            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

}
