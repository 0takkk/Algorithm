import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Main {

    public static int day;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            BigDecimal r = BigDecimal.valueOf(Double.parseDouble(st.nextToken()));
            BigDecimal d = BigDecimal.valueOf(Double.parseDouble(st.nextToken()));
            BigDecimal m = BigDecimal.valueOf(Double.parseDouble(st.nextToken()));

            if(pay(r, d, m)) {
                sb.append(day).append("\n");
            }
            else {
                sb.append("impossible\n");
            }
        }

        System.out.println(sb.toString());
    }
    
    public static boolean pay(BigDecimal r, BigDecimal d, BigDecimal m) {
        day = 0;

        while(day < 1200) {
            BigDecimal interest = d.multiply(r).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
            if(interest.compareTo(m) > 0) return false;

            d = d.add(interest);
            day++;

            d = d.subtract(m);
            if(d.compareTo(BigDecimal.valueOf(0)) <= 0) return true;
        }
        return false;
    }
}
