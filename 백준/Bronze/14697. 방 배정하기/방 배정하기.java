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
        boolean[] room = new boolean[301];
        room[a] = room[b] = room[c] = true;

        for(int i = a; i <= 300; i++) {
            for(int j = 0; j < 3; j++) {
                if(i >= people[j] && room[i-people[j]]) {
                    room[i] = true;
                    break;
                }
            }
        }

        System.out.println(room[n] ? 1 : 0);
    }

}