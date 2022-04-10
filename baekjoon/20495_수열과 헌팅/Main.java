import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] min = new int[n];
        int[] max = new int[n];

        int[] MIN = new int[n];
        int[] MAX = new int[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            MIN[i] = min[i] = a-b;
            MAX[i] = max[i] = a+b;
        }

        Arrays.sort(MIN);
        Arrays.sort(MAX);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++){
            sb.append(lowerBound(MAX, min[i]) + 1).append(" ");
            sb.append(upperBound(MIN, max[i])).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int lowerBound(int[] arr, int target){
        int left = 0;
        int right = arr.length;

        while(left < right){
            int mid = (left + right) / 2;

            if(arr[mid] >= target) right = mid;
            else left = mid+1;
        }

        return left;
    }

    public static int upperBound(int[] arr, int target){
        int left = 0;
        int right = arr.length;

        while(left < right){
            int mid = (left + right) / 2;

            if(arr[mid] > target) right = mid;
            else left = mid+1;
        }

        return left;
    }

}
