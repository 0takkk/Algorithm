import java.io.*;
import java.util.*;

public class Main {

    public static final int MAX = (1 << 20) - 1;
    public static int n;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n+1];

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int comd = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());
            int seat = 0;
            if(comd < 3) seat = Integer.parseInt(st.nextToken());

            command(comd, idx, seat);
        }

        int ans = 0;
        int[] visited = new int[MAX+1];
        for(int i = 1; i <= n; i++){
            if(visited[arr[i]]++ == 0) ans++;
        }

        System.out.println(ans);
    }

    public static void command(int comd, int idx, int seat){
        int train = arr[idx];
        seat -= 1;

        switch (comd){
            case 1:
                arr[idx] = train | (1 << seat);
                break;
            case 2:
//                int num = MAX - (1 << seat);
//                arr[idx] = train & num;
                arr[idx] = train & (~(1 << seat));
                break;
            case 3:
                train = train << 1;
                arr[idx] = train & MAX;
                break;
            default:
                arr[idx] = train >> 1;
                break;
        }
    }

}
