import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == 0 && b == 0) break;

            int totalUse = calWatt(a);

            int left = 0, right = totalUse;

            while(left <= right){
                int mid = (left + right) / 2;

                int result = calPrice(mid);
                int opp = calPrice(totalUse - mid);

                int diff = Math.abs(result - opp);

                if(diff == b){
                    sb.append(result).append("\n");
                    break;
                }

                if(diff > b){
                    left = mid+1;
                }else{
                    right = mid-1;
                }
            }
        }

        System.out.println(sb.toString());
    }

    public static int calWatt(int price){
        if(price <= 200) return price / 2;
        else if(price <= 29900) return (price-200) / 3 + 100;
        else if(price <= 4979900) return (price-29900) / 5 + 10000;
        else return (price-4979900) / 7 + 1000000;
    }

    public static int calPrice(int use){
        if(use <= 100) return use * 2;
        else if(use <= 10000) return (use-100) * 3 + 200;
        else if(use <= 1000000) return (use-10000) * 5 + 29900;
        else return (use-1000000) * 7 + 4979900;
    }

}
