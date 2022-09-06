import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        double[][] arr = new double[n+1][2];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Double.parseDouble(st.nextToken());
            arr[i][1] = Double.parseDouble(st.nextToken());
        }

        arr[n][0] = arr[0][0];
        arr[n][1] = arr[0][1];

        double a = 0, b = 0;

        for(int i = 0; i < n; i++){
            a += (arr[i][0] * arr[i+1][1]);
            b += (arr[i][1] * arr[i+1][0]);
        }

        double ans = Math.abs(a-b) / 2;

        System.out.printf("%.1f%n", ans);
    }
}
