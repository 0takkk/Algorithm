import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String s1 = br.readLine();
        String s2 = br.readLine();

        int idx = 0;
        int s1size = s1.length();
        int s2size = s2.length();

        int count = 0;
        while(idx < s1size){
            if((idx = s1.indexOf(s2, idx)) != -1){
                count++;
                idx += s2size;
            }
            else break;
        }

        System.out.println(count);
    }

}
