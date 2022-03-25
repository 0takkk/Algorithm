import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n;
        StringBuilder sb = new StringBuilder();

        while((n = Integer.parseInt(br.readLine())) != 0){
            int max = Integer.MIN_VALUE;
            int sum = 0;

            for(int i = 0; i < n; i++){
                sum += Integer.parseInt(br.readLine());
                max = Math.max(sum, max);

                if(sum < 0) sum = 0;
            }

            sb.append(max + "\n");
        }

        System.out.println(sb.toString());
    }
}
