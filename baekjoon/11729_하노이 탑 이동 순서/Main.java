import java.io.*;
import java.util.*;

public class Main {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        sb.append((int)Math.pow(2, n) -1 + "\n");
        top(1, 3, 2, n);

        System.out.println(sb.toString());
    }

    public static void top(int from, int to, int sub, int n){
        if(n == 1){
            sb.append(from + " " + to + "\n");
            return;
        }

        top(from, sub, to, n-1);
        sb.append(from + " " + to + "\n");
        top(sub, to, from, n-1);
    }

}
