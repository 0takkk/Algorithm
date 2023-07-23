import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        int[] arr = new int[str.length()];
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '0') arr[i] = 0;
            else arr[i] = 1;
        }

        int pre = -1;
        int zero = 0;
        int one = 0;

        for (int num : arr) {
            if(num != pre){
                if(num == 0) one++;
                else zero++;

                pre = num;
            }
        }

        System.out.println(Math.min(zero, one));
    }

}
