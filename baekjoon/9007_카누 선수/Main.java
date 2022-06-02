import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(t-->0){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] c = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] d = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int[] ab = new int[n*n];
            int[] cd = new int[n*n];

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    ab[n*i + j] = a[i] + b[j];
                }
            }

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    cd[n*i + j] = c[i] + d[j];
                }
            }

            Arrays.sort(ab);

            int ans = 0;
            int min = Integer.MAX_VALUE;
            for (int i : cd) {
                int result = binarySearch(ab, k-i);
                int abs = Math.abs(result);

                if(min > abs){
                    ans = result;
                    min = abs;
                } else if(min == abs && ans < 0){
                    ans = result;
                }
            }

            sb.append((k - ans) + " \n");
        }

        System.out.println(sb.toString());
    }

    public static int binarySearch(int[] arr, int target){
        int result = 0;
        int min = Integer.MAX_VALUE;
        int l = 0;
        int r = arr.length-1;

        while(l <= r){
            int mid = (l + r) / 2;

            int num = target - arr[mid];
            int abs = Math.abs(num);

            if(min > abs){
                min = abs;
                result = num;
            } else if(min == abs && result < 0){
                result = num;
            }

            if(num > 0) l = mid+1;
            else if(num < 0) r = mid-1;
            else return 0;
        }

        return result;
    }

}
