import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        int[] arr = new int[26];
        Arrays.fill(arr, -1);

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);

            if(arr[c-'a'] != -1) continue;

            arr[c-'a'] = i;
        }

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
