import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        double[] arr = new double[n];

        for(int i = 0; i < n; i++) {
            arr[i] = Double.parseDouble(br.readLine());
        }

        double ans = arr[0];
        for(int i = 1; i < n; i++) {
            arr[i] = Math.max(arr[i], arr[i-1] * arr[i]);
            ans = Math.max(ans, arr[i]);
        }

        System.out.printf("%.3f", ans);
    }

}