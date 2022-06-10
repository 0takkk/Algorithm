import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        int n = Integer.parseInt(str);
        int len = str.length();

        for(int i = n - (9*len); i <= n; i++){
            int sum = i;
            int num = i;

            while(num != 0){
                sum += num % 10;
                num /= 10;
            }

            if(sum == n) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(0);
    }

}
