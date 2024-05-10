import java.io.*;
import java.util.*;

public class Main {

    public static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String s = br.readLine();
        String t = br.readLine();

        dfs(s, t);
        System.out.println(flag ? 1 : 0);
    }

    public static void dfs(String s, String t) {
        if(flag) return;
        if(s.length() == t.length()) {
            if(s.equals(t)) flag = true;
            return;
        }

        if(t.charAt(t.length()-1) == 'A') dfs(s, t.substring(0, t.length()-1));
        if(t.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(t.substring(1));
            dfs(s, sb.reverse().toString());
        }
    }

}