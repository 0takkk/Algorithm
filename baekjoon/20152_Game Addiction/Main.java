import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int m = Math.abs(h-n);

        long[][] arr = new long[m+1][m+1];

        for(int i = 0; i <= m; i++){
            arr[0][i] = 1;
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= m; j++){
                if(i > j) continue;

                arr[i][j] += arr[i-1][j];
                arr[i][j] += arr[i][j-1];
            }
        }

        System.out.println(arr[m][m]);
    }

}
