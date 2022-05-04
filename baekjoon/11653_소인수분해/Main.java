import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        boolean[] prime = new boolean[n+1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        for(int i = 2; i <= Math.sqrt(n); i++){
            if(prime[i]){
                for(int j = i*i; j <= n; j+=i){
                    prime[j] = false;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while(n > 1){
            for(int i = 2; i <= n; i++){
                if(prime[i] && n % i == 0){
                    sb.append(i + "\n");
                    n /= i;
                    break;
                }
            }
        }

        System.out.println(sb.toString());
    }

}
