import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] rdp = new int[n];
        int[] ldp = new int[n];
        Arrays.fill(rdp, 1);
        Arrays.fill(ldp, 1);

        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j]){
                    rdp[i] = Math.max(rdp[i], rdp[j]+1);
                }
            }
        }

        for(int i = n-1; i >= 0; i--){
            for(int j = n-1; j > i; j--){
                if(arr[i] > arr[j]){
                    ldp[i] = Math.max(ldp[i], ldp[j]+1);
                }
            }
        }

        int max = 0;
        for(int i = 0; i < n; i++){
            max = Math.max(max, rdp[i] + ldp[i]);
        }

        System.out.println(max-1);
    }

}
