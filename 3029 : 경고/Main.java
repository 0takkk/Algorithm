import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String start = br.readLine();
        String end = br.readLine();

        int startTime = timeToSec(start);
        int endTime = timeToSec(end);

        if(startTime >= endTime)
            endTime += 24 * 3600;

        int waitTime = endTime - startTime;

        System.out.println(secToTime(waitTime));
    }

    public static int timeToSec(String time){
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 3600 + Integer.parseInt(split[1]) * 60 + Integer.parseInt(split[2]);
    }

    public static String secToTime(int time){
        int h = time / 3600;
        time -= h*3600;

        int m = time / 60;
        time -= m*60;

        int s = time % 60;

        return String.format("%02d:%02d:%02d", h, m, s);
    }

}
