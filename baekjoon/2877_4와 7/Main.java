import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int k = Integer.parseInt(br.readLine());
        int n = 1;

        while(k > Math.pow(2, n)){
            k -= Math.pow(2, n);
            n++;
        }

        int size = n;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < size; i++){
            int tmp = (int)Math.pow(2, n);

            if(k <= tmp/2){
                sb.append(4);
            }else{
                sb.append(7);
                k -= tmp/2;
            }
            n--;
        }

        System.out.println(sb.toString());
    }

}
