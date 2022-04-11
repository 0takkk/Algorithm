import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int gcdVal = arr[1] - arr[0];
        for(int i = 2; i < n; i++){
            gcdVal = gcd(gcdVal, arr[i] - arr[i-1]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= gcdVal; i++){
            if(gcdVal % i == 0){
                sb.append(i + " ");
            }
        }

        System.out.println(sb.toString());
    }

    public static int gcd(int a, int b){
        while(b != 0){
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

}
