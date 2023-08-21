import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = br.readLine();
        int len = input.length();

        String[] ans = new String[len];
        for(int i = 0; i < len; i++) {
            ans[i] = input.substring(i, len);
        }

        Arrays.sort(ans);

        StringBuilder sb = new StringBuilder();
        for (String an : ans) {
            sb.append(an).append("\n");
        }

        System.out.println(sb.toString());
    }

}
