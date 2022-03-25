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
            for(int j = i+i; j <= n; j+=i){
                prime[j] = false;
            }
        }


        Queue<Integer> q = new LinkedList<>();
        int sum = 0;
        int count = 0;

        for(int i = 0; i <= n; i++){
            if(prime[i]){
                sum += i;
                q.offer(i);

                if(sum == n) count++;

                while(sum > n && !q.isEmpty()){
                    sum -= q.poll();
                    if(sum == n) count++;
                }
            }
        }

        System.out.println(count);
    }

}
