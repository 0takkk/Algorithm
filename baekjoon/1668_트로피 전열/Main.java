import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for(int i = 0; i < n ; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int front = 0;
        int back = 0;

        int h = 0;
        for(int i = 0; i < n; i++){
            if(arr[i] > h) front++;
            h = Math.max(h, arr[i]);
        }

        h = 0;
        for(int i = n-1; i >= 0; i--){
            if(arr[i] > h) back++;
            h = Math.max(h, arr[i]);
        }

        System.out.println(front);
        System.out.println(back);
    }
    
}
