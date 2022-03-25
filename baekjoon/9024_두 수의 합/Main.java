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
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int left = 0;
            int right = arr.length-1;
            int count = 0;
            int diff = Integer.MAX_VALUE;

            while(left < right){
                int sum = arr[left] + arr[right];

                if(Math.abs(sum - k) < diff){
                    count = 1;
                    diff = Math.abs(sum - k);
                } else if(Math.abs(sum - k) == diff){
                    count++;
                }

                if(sum == k){
                    left++;
                    right--;
                } else if(sum > k){
                    right--;
                } else{
                    left++;
                }

            }

            sb.append(count + "\n");
        }

        System.out.println(sb.toString());
    }

}
