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
//            double r = Double.parseDouble(st.nextToken());
//            double d = Double.parseDouble(st.nextToken());
//            double m = Double.parseDouble(st.nextToken());

            BigDecimal r = BigDecimal.valueOf(Double.parseDouble(st.nextToken()));
            BigDecimal d = BigDecimal.valueOf(Double.parseDouble(st.nextToken()));
            BigDecimal m = BigDecimal.valueOf(Double.parseDouble(st.nextToken()));

//            System.out.println("r = " + r);
//            System.out.println("d = " + d);
//            System.out.println("m = " + m);

//            double r = Double.parseDouble(st.nextToken());
//            int d = (int) (Double.parseDouble(st.nextToken()) * 100);
//            int m = (int) (Double.parseDouble(st.nextToken()) * 100);

            if(pay(r, d, m)) {
                sb.append(day).append("\n");
            }
            else {
                sb.append("impossible\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static boolean pay(double r, int d, int m) {
        day = 0;

        while(day < 1200) {
            int interest = (int) Math.round(d * r / 100);
            if(interest > m) return false;

            d += interest;
            day++;
//            System.out.println("d = " + d);
//            System.out.println("day = " + day);

            d -= m;
//            System.out.println("d = " + d);
//            System.out.println();
            if(d <= 0) return true;
        }
        return false;
    }

    public static boolean pay(BigDecimal r, BigDecimal d, BigDecimal m) {
        day = 0;

        while(day < 1200) {
            BigDecimal interest = d.multiply(r).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
//            System.out.println("interest = " + interest);
            if(interest.compareTo(m) > 0) return false;

            d = d.add(interest);
            day++;
//            System.out.println("d = " + d);
//            System.out.println("day = " + day);

            d = d.subtract(m);
//            System.out.println("d = " + d);
//            System.out.println();
            if(d.compareTo(BigDecimal.valueOf(0)) <= 0) return true;
        }
        return false;
    }

//    public static boolean pay(double r, double d, double m) {
//        day = 0;
//
//        while(day < 1200) {
//            int interest = (int) Math.round(d * r / 100);
//            if(interest > m) return false;
//
//            d += interest;
//            day++;
//            System.out.println("d = " + d);
//            System.out.println("day = " + day);
//
//            d -= m;
//            System.out.println("d = " + d);
//            System.out.println();
//            if(d <= 0) return true;
//        }
//        return false;
//    }
}
