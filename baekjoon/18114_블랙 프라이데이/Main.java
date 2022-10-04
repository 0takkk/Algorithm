import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        System.out.println(twoPoint(arr, n, c));
    }


    public static int twoPoint(int[] arr, int n, int c){
        for(int i = 0; i < n-2; i++){
            if(arr[i] == c) return 1;
            int left = i+1;
            int right = n-1;

            while(left < right){
                int sum = arr[i] + arr[left] + arr[right];

                if(sum == c || sum - arr[left] == c || sum - arr[right] == c){
                    return 1;
                }

                if(sum > c) right--;
                else left++;
            }
        }

        return 0;
    }

}
