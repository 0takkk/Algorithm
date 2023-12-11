import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = br.readLine();
        input = input.replaceAll("XXXX", "AAAA");
        input = input.replaceAll("XX", "BB");

        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == 'X') {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(input);
    }

}