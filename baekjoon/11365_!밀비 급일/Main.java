import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder result = new StringBuilder();

        while(true){
            String str = br.readLine();
            if(str.equals("END")) break;

            StringBuilder sb = new StringBuilder(str);
            sb.reverse();
            result.append(sb.toString() + "\n");
        }

        System.out.println(result.toString());
    }
    
}
