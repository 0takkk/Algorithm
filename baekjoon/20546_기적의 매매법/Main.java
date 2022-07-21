import java.io.*;
import java.util.*;

public class Main {

    public static class Stock{
        int num, restCash;

        public Stock(int num, int restCash) {
            this.num = num;
            this.restCash = restCash;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int cash = Integer.parseInt(br.readLine());
        int n = 14;
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stock b = new Stock(0, cash);
        Stock t = new Stock(0, cash);

        for(int i = 0; i < n; i++){
            if(b.restCash >= arr[i]){
                int num = b.restCash / arr[i];
                b = new Stock(num, b.restCash - arr[i] * num);
            }
        }

        for(int i = 3; i < n; i++){
            if(arr[i-3] < arr[i-2] && arr[i-2] < arr[i-1] && arr[i-1] < arr[i]){
                int sell = t.num * arr[i];
                t = new Stock(0, t.restCash + sell);
            }

            if(arr[i-3] > arr[i-2] && arr[i-2] > arr[i-1] && arr[i-1] > arr[i]){
                if(t.restCash >= arr[i]){
                    int buyNum = t.restCash / arr[i];
                    t = new Stock(t.num + buyNum, t.restCash - arr[i] * buyNum);
                }
            }
        }

        int totalB = b.restCash + b.num * arr[n-1];
        int totalT = t.restCash + t.num * arr[n-1];

        if(totalB > totalT) System.out.println("BNP");
        else if(totalB < totalT) System.out.println("TIMING");
        else System.out.println("SAMESAME");
    }

}
