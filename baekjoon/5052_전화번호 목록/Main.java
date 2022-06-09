import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            n = Integer.parseInt(br.readLine());

            arr = new String[n];
            for(int i = 0; i < n; i++)
                arr[i] = br.readLine();

            Arrays.sort(arr);

            if(check()) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean check(){
        for(int i = 0; i < n-1; i++){
            if(arr[i+1].startsWith(arr[i])) return false;
        }
        return true;
    }

}
