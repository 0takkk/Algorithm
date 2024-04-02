import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0) {
            int n = Integer.parseInt(br.readLine());

            String init = br.readLine();
            String target = br.readLine();

            int[] diff = new int[2];

            for(int i = 0; i < n; i++) {
                char c1 = init.charAt(i);
                char c2 = target.charAt(i);

                if(c1 != c2) {
                    if(c1 == 'B') diff[0]++;
                    else diff[1]++;
                }
            }

            sb.append(Math.max(diff[0], diff[1])).append("\n");
        }

        System.out.println(sb.toString());
    }

}