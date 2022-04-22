import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String s = br.readLine();
        String t = br.readLine();

        System.out.println(solve(s, t));
    }

    public static int solve(String s, String t){
        if(s.length() == t.length()){
            if(s.equals(t)) return 1;
            return 0;
        }

        int result = 0;
        if(t.charAt(t.length()-1) == 'A') result += solve(s, t.substring(0, t.length()-1));
        if(t.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(t.substring(1));
            result += solve(s, sb.reverse().toString());
        }

        return result > 0 ? 1 : 0;
    }

}
