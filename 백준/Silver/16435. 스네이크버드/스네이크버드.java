import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] food = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            food[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(food);

        for(int i = 0; i < n; i++) {
            if(food[i] > l) break;
            l++;
        }

        System.out.println(l);
    }

}
