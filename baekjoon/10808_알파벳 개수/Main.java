import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] alpa = new int[26];

        String str = br.readLine();

        for(int i = 0; i < str.length(); i++){
            alpa[str.charAt(i) - 'a']++;
        }

        for(int i = 0; i < 26; i++)
            System.out.print(alpa[i] + " ");
    }

}
