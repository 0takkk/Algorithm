import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++){
            if(i == n){
                if(n % 2 == 1) sb.append("3");
                else sb.append("2");
                continue;
            }

            if(i % 2 == 1) sb.append("1 ");
            else sb.append("2 ");
        }

        System.out.println(sb.toString());
    }
    
}
