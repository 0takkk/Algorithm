import java.io.*;
import java.util.*;

public class Main {

    public static long[] min;
    public static String[] max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        min = new long[101];
        max = new String[101];

        setMin();
        setMax();

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            int n = Integer.parseInt(br.readLine());

            sb.append(min[n]).append(" ").append(max[n]).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void setMin(){
        Arrays.fill(min, Long.MAX_VALUE);
        min[2] = 1; min[3] = 7; min[4] = 4;
        min[5] = 2; min[6] = 6; min[7] = 8; min[8] = 10;

        String[] add = {"1", "7", "4", "2", "0", "8"};

        for(int i = 9; i <= 100; i++){
            for(int j = 2; j <= 7; j++){
                String num = min[i-j] + add[j-2];
                min[i] = Math.min(Long.parseLong(num), min[i]);
            }
        }
    }

    public static void setMax(){
        max[2] = "1"; max[3] = "7";

        for(int i = 4; i <= 100; i++){
            max[i] = max[i-2] + "1";
        }
    }

}
