import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();

        int ans = 0;
        boolean isNewE = true;

        for(int i = 0; i < n; i++) {
            if(isNewE && arr[i] == 'E') {
                isNewE = false;
                ans++;
            }

            if(arr[i] == 'W') {
                isNewE = true;
            }
        }

        System.out.println(ans);
    }
    
}
