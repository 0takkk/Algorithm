import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] x = new int[n];
        int[] y = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        int size = x[n - 1] + 2;
        int[] skyline = new int[size];
        for (int i = 0; i < n - 1; i++) {
            for (int j = x[i]; j < x[i + 1]; j++) {
                skyline[j] = y[i];
            }
        }

        skyline[x[n - 1]] = y[n - 1];

        int ans = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i < size; i++) {
            int height = skyline[i];

            if (!stack.isEmpty()) {
                if(stack.peek() == height) continue;
                else if(stack.peek() < height){
                    stack.push(height);
                }
                else{
                    while(!stack.isEmpty() && stack.peek() > height){
                        stack.pop();
                        ans++;
                    }

                    if(height != 0 && (stack.isEmpty() || stack.peek() != height)){
                        stack.push(height);
                    }
                }
            }
            else if(height != 0){
                stack.push(height);
            }
        }

        while(!stack.isEmpty()){
            stack.pop();
            ans++;
        }

        System.out.println(ans);
    }

}
