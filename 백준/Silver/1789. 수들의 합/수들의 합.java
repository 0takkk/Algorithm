import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long s = Long.parseLong(br.readLine());
        int max = (int)Math.sqrt(2*s);
        int cnt = 0;

        for(int i = 1; i <= max; i++) {
            if(s < i) break;
            cnt++;
            s -= i;
        }

        System.out.println(cnt);
    }

}
