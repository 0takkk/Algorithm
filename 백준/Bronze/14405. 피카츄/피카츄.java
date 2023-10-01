import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        HashSet<String> set = new HashSet<>(List.of("pi", "ka", "chu"));
        String input = br.readLine();
        int idx = 0;

        StringBuilder sb = new StringBuilder();
        while(idx < input.length()) {
            sb.append(input.charAt(idx++));

            if(sb.length() > 3) {
                System.out.println("NO");
                return;
            }

            if(set.contains(sb.toString())) {
                sb = new StringBuilder();
            }
        }

        if(sb.length() == 0 || set.contains(sb.toString())) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }

}
