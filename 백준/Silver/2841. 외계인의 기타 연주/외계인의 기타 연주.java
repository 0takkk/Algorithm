import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        Stack<Integer>[] fingers = new Stack[7];
        for(int i = 1; i <= 6; i++) {
            fingers[i] = new Stack<>();
        }
        int ans = 0;

        st = new StringTokenizer(br.readLine());
        int line = Integer.parseInt(st.nextToken());
        int fret = Integer.parseInt(st.nextToken());
        fingers[line].push(fret);
        ans = 1;
        n--;

        while(n-->0) {
            st = new StringTokenizer(br.readLine());
            line = Integer.parseInt(st.nextToken());
            fret = Integer.parseInt(st.nextToken());

            if(fingers[line].isEmpty()) {
                fingers[line].push(fret);
                ans++;
            }
            else {
                while(!fingers[line].isEmpty() && fingers[line].peek() > fret) {
                    fingers[line].pop();
                    ans++;
                }

                if(fingers[line].isEmpty() || fingers[line].peek() < fret) {
                    fingers[line].add(fret);
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }

}
