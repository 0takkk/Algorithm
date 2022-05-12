import java.io.*;
import java.util.*;

public class Main {

    public static String p;
    public static Deque<String> dq;
    public static boolean front;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String str = br.readLine();

            String[] arr = str.substring(1, str.length() - 1).split(",");
            dq = new ArrayDeque<>();
            for(int i = 0; i < n; i++){
                dq.offer(arr[i]);
            }

            if(game()){
                StringBuilder sb1 = new StringBuilder();
                sb1.append("[");
                while(!dq.isEmpty()){
                    if(front) sb1.append(dq.pollFirst());
                    else sb1.append(dq.pollLast());

                    if(!dq.isEmpty()) sb1.append(",");
                }

                sb1.append("]");

                sb.append(sb1.toString()+"\n");
            }else{
                sb.append("error\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static boolean game(){
        front = true;

        for(int i = 0; i < p.length(); i++){
            char c = p.charAt(i);

            if(c == 'R') front = !front;
            else{
                if(dq.isEmpty()) return false;

                if(front){
                    dq.pollFirst();
                }else {
                    dq.pollLast();
                }
            }
        }

        return true;
    }

}
