import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int result = 0;

        for(int h = 0; h <= n; h++){
            if(h % 10 == k || h / 10 == k){
                result += 3600;
            }
            else{
                for(int m = 0; m < 60; m++){
                    if(m % 10 == k || m / 10 == k){
                        result += 60;
                    }
                    else{
                        for(int s = 0; s < 60; s++){
                            if(s % 10 == k || s / 10 == k){
                                result += 1;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }

}
