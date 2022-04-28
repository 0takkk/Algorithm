import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(br.readLine());

            int count = 0;
            int start = getSecond(st.nextToken());
            int end = getSecond(st.nextToken());

            if(start > end) end += 24*3600;

            for(int j = start; j <= end; j++){
                if(getTime(j) % 3 == 0) count++;
            }

            System.out.println(count);
        }
    }

    public static int getSecond(String s){
        String[] split = s.split(":");
        return Integer.parseInt(split[2]) + Integer.parseInt(split[1]) * 60 + Integer.parseInt(split[0]) * 3600;
    }

    public static int getTime(int time){
        int h = time / 3600;
        time -= h * 3600;
        int m = time / 60;
        time -= m * 60;
        int s = time % 60;

        if(h >= 24) h -= 24;

        String format = String.format("%02d%02d%02d", h, m, s);
        return Integer.parseInt(format);
    }

}
