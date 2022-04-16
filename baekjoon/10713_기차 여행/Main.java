import java.io.*;
import java.util.*;

public class Main {

    public static class Track{
        int a, b, c;

        public Track(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] route = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            route[i] = Integer.parseInt(st.nextToken());
        }

        int[] count = new int[n+1];
        for(int i = 1; i < m; i++){
            int start = route[i-1];
            int end = route[i];

            int max = Math.max(start, end);
            int min = Math.min(start, end);

            count[min]++;
            count[max]--;
        }

        Track[] tracks = new Track[n];
        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tracks[i] = new Track(a, b, c);
        }

        long result = 0;
        long psum = 0;

        for(int i = 1; i < n; i++){
            psum += count[i];
            result += Math.min(psum * tracks[i].a, psum * tracks[i].b + tracks[i].c);
        }

        System.out.println(result);
    }

}
