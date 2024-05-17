import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = br.readLine();
        System.out.println(calcMax(input));
        System.out.println(calcMin(input));
    }

    public static String calcMax(String input) {
        StringBuilder sb = new StringBuilder();
        int mCount = 0;

        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == 'M') mCount++;
            else {
                sb.append(5).append("0".repeat(mCount));
                mCount = 0;
            }
        }

        if(mCount > 0) {
            sb.append("1".repeat(mCount));
        }

        return sb.toString();
    }

    public static String calcMin(String input) {
        StringBuilder sb = new StringBuilder();
        int mCount = 0;

        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == 'M') mCount++;
            else {
                if(mCount > 0) {
                    sb.append(1).append("0".repeat(mCount-1)).append(5);
                }
                else {
                    sb.append("5");
                }
                mCount = 0;
            }
        }

        if(mCount > 0) {
            sb.append(1).append("0".repeat(mCount-1));
        }

        return sb.toString();
    }


}
