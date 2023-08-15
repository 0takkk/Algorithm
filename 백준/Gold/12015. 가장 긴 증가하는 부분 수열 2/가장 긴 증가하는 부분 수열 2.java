import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = new int[n];
        int length = 1;
        lis[0] = arr[0];

        for(int i = 1; i < n; i++) {
            int num = arr[i];

            if(lis[length-1] < num) {
                length++;
                lis[length-1] = num;
            }
            else {
                int left = 0;
                int right = length;

                while(left < right) {
                    int mid = (left + right) / 2;

                    if(lis[mid] < num) {
                        left = mid+1;
                    }
                    else {
                        right = mid;
                    }
                }

                lis[left] = num;
            }
        }

        System.out.println(length);
    }

}
