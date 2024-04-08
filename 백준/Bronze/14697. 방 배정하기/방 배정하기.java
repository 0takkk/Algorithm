import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] people = new int[] {a, b, c};
        boolean[] room = new boolean[n+1];
        room[a] = room[b] = room[c] = true;

        for(int i = 1; i <= n; i++) {
            if(room[i]) {
                for(int j = 0; j < 3; j++) {
                    if(i + people[j] <= n) {
                        room[i+people[j]] = true;
                    }
                }
            }
        }

        System.out.println(room[n] ? 1 : 0);
    }

}